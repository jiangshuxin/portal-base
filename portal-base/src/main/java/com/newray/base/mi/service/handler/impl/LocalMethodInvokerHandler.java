package com.newray.base.mi.service.handler.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.aop.support.AopUtils;

import com.newray.base.mi.command.InvokerResult;
import com.newray.base.mi.exception.MIException;
import com.newray.base.mi.service.desc.LocalMethodDesc;
import com.newray.base.mi.service.handler.MethodInvokerHandler;
import com.newray.base.spring.SpringContextHolder;
import com.newray.base.util.ReflectUtil;

/**
 * 本地方法调用处理器
 * @see LocalMethodDesc
 * @author justin.jiang
 *
 */
public class LocalMethodInvokerHandler implements MethodInvokerHandler {
	protected static final Logger logger = Logger.getLogger(LocalMethodInvokerHandler.class);
	
	@Override
	public InvokerResult handle(Object obj,String methodName,List<String> paramTypes,Map<String, ?>... paramMap) throws MIException{
		InvokerResult result = new InvokerResult();
		try {
			if(obj instanceof LocalMethodDesc){
				LocalMethodDesc desc = (LocalMethodDesc)obj;
				if(StringUtils.isNotEmpty(desc.getBeanId())){
					Object target = SpringContextHolder.getBean(desc.getBeanId());
					Method method = null;
					List<Object> params = new ArrayList<Object>();
					methodName = StringUtils.isEmpty(methodName)?desc.getMethodName():methodName;
					List<String> newParamTypes = isEmptyInArr(paramTypes)?desc.getParamTypes():paramTypes;
					
					List<Class<?>> clazzs = new ArrayList<Class<?>>();
					if(newParamTypes != null && newParamTypes.size() > 0){
						for(String paramType : newParamTypes){
							if(StringUtils.isNotEmpty(paramType)){
								clazzs.add(Class.forName(paramType.toString()));
							}
						}
					}
					
					if(newParamTypes.size() > 0 && paramMap.length > 0 && newParamTypes.size() == paramMap.length){//指定参数类型的，并且参数类型个数和实际参数个数一致，根据参数类型匹配
						method = MethodUtils.getMatchingAccessibleMethod(target.getClass(), methodName, clazzs.toArray(new Class<?>[0]));
						if(method == null) throw new MIException(String.format("can't find local method:%s.%s%s", AopUtils.getTargetClass(target).getName(),methodName,newParamTypes.toString()));
						for(int i=0;i<clazzs.size();i++){
							if(ReflectUtil.isPrimitive(clazzs.get(i))){//基本类型或者String，都会有带String的构造方法
								Object p = org.springframework.beans.BeanUtils.instantiateClass(clazzs.get(i).getConstructor(String.class),paramMap[i].values().toArray()[0]);
								params.add(p);
							}else{
								//其他则认为是自定义对象的形式，将map中的参数全部设置到该对象中
								Object p = org.springframework.beans.BeanUtils.instantiate(clazzs.get(i));
								BeanUtils.populate(p, paramMap[i]);
								params.add(p);
							}
						}
					}else{//未指定参数类型的，根据方法名匹配，参数个数匹配
						Method[] methods = target.getClass().getMethods();
						for(Method m : methods){
							//方法名匹配，参数个数匹配
							if(methodName.equalsIgnoreCase(m.getName())){
								if((newParamTypes.size() == 0 && m.getParameterTypes().length == 0)//表达式意思为无参方法
									|| (newParamTypes.size() == m.getParameterTypes().length)//指定的参数个数与当前方法参数个数匹配
									|| (paramMap.length == m.getParameterTypes().length && isObjMatch(paramMap,m.getParameterTypes()))){//实际的参数个数与当前方法参数个数匹配
									method = m;
									method.setAccessible(true);
									Class<?>[] classs = method.getParameterTypes();
									if(classs.length < paramMap.length) throw new MIException("classs' length is less than paramMap's length");
									for(int i=0;i<classs.length;i++){
										if(ReflectUtil.isPrimitive(classs[i])){//基本类型或者String
											Object p = org.springframework.beans.BeanUtils.instantiateClass(classs[i].getConstructor(String.class),paramMap[i].values().toArray()[0]);
											params.add(p);
										}else{
											Object p = org.springframework.beans.BeanUtils.instantiate(classs[i]);
											BeanUtils.populate(p, paramMap[i]);
											params.add(p);
										}
									}
									break;
								}
							}
						}
					}
					if(method == null) throw new MIException(String.format("can't find local method:%s.%s%s", AopUtils.getTargetClass(target).getName(),methodName,newParamTypes.toString()));
					
					Object r = null;
					try {
						r = method.invoke(target, params.toArray(new Object[0]));
						result.setSuccessFlag(true);
					} catch (Exception e) {
						result.setSuccessFlag(false);
						result.setErrorMsg(e.getCause().toString());
						logger.warn("method invoke error", e);
					}
					result.setContent(r);
				}
			}else{
				logger.warn(String.format("参数类型%s不正确", obj.getClass().getName()));
				result.setErrorMsg(String.format("参数类型%s不正确", obj.getClass().getName()));
			}
		} catch (Exception e) {
			throw new MIException("local method invoke error",e);
		}
		return result;
	}

	/**
	 * 实际参数类型与指定参数类型进行匹配
	 * @param paramMap
	 * @param parameterTypes
	 * @return
	 */
	private boolean isObjMatch(Map<String, ?>[] paramMap,
			Class<?>[] parameterTypes) {
		if(paramMap.length != parameterTypes.length) return false;
		for(int i=0;i<paramMap.length;i++){
			Map<String, ?> map = paramMap[i];
			Class<?> clazz = parameterTypes[i];
			if((ReflectUtil.isPrimitive(clazz) && !ReflectUtil.isAllNumber(map.keySet())) || 
					(!ReflectUtil.isPrimitive(clazz) && ReflectUtil.isAllNumber(map.keySet()))) return false;
		}
		return true;
	}

	/**
	 * 判断每个元素是否都为空
	 * @param paramTypes
	 * @return
	 */
	private boolean isEmptyInArr(List<String> paramTypes) {
		if(paramTypes == null || paramTypes.size() == 0) return true;
		for(String paramType : paramTypes){
			if(StringUtils.isNotEmpty(paramType))return false;
		}
		return true;
	}
}
