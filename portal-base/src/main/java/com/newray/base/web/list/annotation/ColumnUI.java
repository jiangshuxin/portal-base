package com.newray.base.web.list.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * list页面列注解
 * @author hlpeng
 *
 */
@Target({METHOD})
@Retention(RUNTIME)
public @interface ColumnUI {
	
	String title() default "";
	
	String field() default "";
	
	int width() default 100;
	
	short rowspan() default 0;
	
	short colspan() default 0;
	
	String align() default "center";
	
	boolean sortable() default false;
	
	boolean resizable() default false;
	
	boolean hidden() default false;
	
	boolean checkbox() default false;
	
	boolean frozen() default false;
	
	/**
	 * 转换代码
	 * @return
	 */
	String dictNo() default "";
	
	/**
	 * 转换代码关联字段
	 * @return
	 */
	String dictRefColumn() default "";
	
	String order() default "";
}
