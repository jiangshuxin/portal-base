package com.newray.base.util;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;

public class ReflectUtil {
	/**
	 * 获取指定注解的PropertyDescriptor集合，从ReadMethod搜索
	 * @param pds
	 * @param clazz
	 * @return
	 */
	public static PropertyDescriptor[] filter(PropertyDescriptor[] pds,Class<? extends Annotation> clazz){
		if(pds == null || clazz == null) return pds;
		List<PropertyDescriptor> list = new ArrayList<PropertyDescriptor>();
		for(PropertyDescriptor pd : pds){
			Method method = pd.getReadMethod();
			if(method.isAnnotationPresent(clazz)){
				list.add(pd);
			}
		}
		return list.toArray(new PropertyDescriptor[]{});
	}
	
	/**
	 * 获取类的泛型类型
	 * @param clazz 
	 * @return 所有泛型集合
	 */
	public static List<Class<?>> getGenericSuperclass(Class<?> clazz){
		Type[] types = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();
		List<Class<?>> list = new ArrayList<Class<?>>();
		if(types == null || types.length == 0) return list;
		for(Type type : types){
			if(type instanceof Class<?>){
				list.add((Class<?>)type);
			}
		}
		return list;
	}
	
	/**
	 * 判断是否为基本类型，包括String
	 * @param clazz
	 * @return
	 */
	public static boolean isPrimitive(Class<?> clazz){
		if(Number.class.isAssignableFrom(clazz) || clazz.isAssignableFrom(String.class) || clazz.isAssignableFrom(Boolean.class)){
			return true;
		}
		if(clazz.isPrimitive()){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断都是数字
	 * @param keySet
	 * @return
	 */
	public static boolean isAllNumber(Collection<String> keySet) {
		for(String key : keySet){
			if(!NumberUtils.isNumber(key)) return false;
		}
		return true;
	}
}
