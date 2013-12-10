package com.newray.base.web.list.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 查询条件注解
 * @author justin.jiang
 *
 */
@Target({METHOD})
@Retention(RUNTIME)
public @interface QueryCondition {
	String name() default "";
	String order() default "";
}
