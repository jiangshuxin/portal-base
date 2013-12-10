package com.newray.base.spring.aop;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.FieldUtils;
import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;

import com.googlecode.genericdao.search.SearchResult;
import com.newray.base.dict.service.DictService;
import com.newray.base.web.list.annotation.ColumnUI;

/**
 * 本类的职责是结合model层的注解配置，对从GeneralCRUDService中获取的数据进行代码转换
 * <p>
 * 通过动态代理完成对GeneralCRUDService的方法调用监听
 * @see com.newray.base.crud.service.GeneralCRUDService
 * @see com.newray.base.web.list.annotation.ColumnUI
 * @author justin.jiang
 *
 */
public class AutoDictConvert implements AfterReturningAdvice {
	protected static final Logger logger = Logger.getLogger(AutoDictConvert.class);
	private DictService dictService;

	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		if(returnValue instanceof SearchResult<?>){
			SearchResult<?> results = (SearchResult<?>)returnValue;
			convertList(results.getResult());
		}else if(returnValue instanceof List){
			convertList(returnValue);
		}else {
			convertSingle(returnValue);
		}
	}

	/**
	 * 转换List集合
	 * @param returnValue
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private void convertList(Object returnValue) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<?> list = (List<?>)returnValue;
		for(Object obj : list){
			convertSingle(obj);
		}
	}

	/**
	 * 转换单个对象
	 * @param obj
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	private void convertSingle(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(obj.getClass());
		pds = com.newray.base.util.ReflectUtil.filter(pds, ColumnUI.class);
		for(PropertyDescriptor pd : pds){
			Method readMethod = pd.getReadMethod();
			ColumnUI columnUI = AnnotationUtils.getAnnotation(readMethod, ColumnUI.class);
			String dictNo = columnUI.dictNo();
			String reference = columnUI.dictRefColumn();
			if(StringUtils.isEmpty(dictNo) || StringUtils.isEmpty(reference)) continue;
			Object itemNoObj = FieldUtils.readField(obj, reference,true);
			if(itemNoObj == null) continue;
			String itemNo = itemNoObj.toString();
			if(dictService.getDictMap(dictNo) == null) {
				logger.warn(String.format("%s.%s has no dict called \"%s\"", obj.getClass().getName(),pd.getName(),dictNo));
				continue;
			}
			pd.getWriteMethod().invoke(obj, dictService.getDictItemName(dictNo, itemNo));
		}
	}

	@Autowired
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
}
