package com.newray.base.web.list.comparator;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

/**
 * 注解order属性比较器
 * @author justin.jiang
 *
 */
public class AnnoOrderComparator implements Comparator<PropertyDescriptor> {
	/**
	 * 参与排序的注解必须要有order属性
	 */
	public static final String ANNOTATION_ORDER = "order";
	private Class<? extends Annotation> clazz;
	
	public AnnoOrderComparator(Class<? extends Annotation> clazz) {
		super();
		Assert.notNull(clazz);
		this.clazz = clazz;
	}

	@Override
	public int compare(PropertyDescriptor pd1, PropertyDescriptor pd2) {
		Method readMethod1 = pd1.getReadMethod();
		Method readMethod2 = pd2.getReadMethod();
		boolean hasAnn1 = readMethod1.isAnnotationPresent(clazz);
		boolean hasAnn2 = readMethod2.isAnnotationPresent(clazz);
		
		if(hasAnn1 && hasAnn2){
			Annotation col1 = readMethod1.getAnnotation(clazz);
			Annotation col2 = readMethod2.getAnnotation(clazz);
			Map<String,Object> map1 = AnnotationUtils.getAnnotationAttributes(col1);
			Map<String,Object> map2 = AnnotationUtils.getAnnotationAttributes(col2);
			
			if(!map1.containsKey(ANNOTATION_ORDER) || !map2.containsKey(ANNOTATION_ORDER) 
					|| StringUtils.isEmpty(map1.get(ANNOTATION_ORDER).toString()) || StringUtils.isEmpty(map2.get(ANNOTATION_ORDER).toString())){
				return -1;
			}
			int result = map1.get(ANNOTATION_ORDER).toString().compareTo(map2.get(ANNOTATION_ORDER).toString());
			if(result == 0){
				return -1;
			}else return result;
		}
		return 0;//treeset add不进去
	}
}
