package com.newray.base.web.info.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * info页面字段注解
 * @author qfang
 *
 */
@Target({METHOD})
@Retention(RUNTIME)
public @interface InfoUI {
	
	String title() default "";
	
	String id() default "";
	
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
	
	String code() default "";
	
	String order() default "";
	
	boolean isRequired() default false;
	
	String validClass() default "";
	
	String validType() default "";
	
	String groupId() default "";
}
