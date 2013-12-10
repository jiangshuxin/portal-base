package com.newray.base.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

public class SpringContextHolder implements ApplicationContextAware {
	private static ApplicationContext springContext;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		springContext = applicationContext;
	}

	public static <T> T getBean(Class<T> requiredType) throws BeansException{
		return getContext().getBean(requiredType);
	}

	public static Object getBean(String name) throws BeansException{
		return getContext().getBean(name);
	}

	public static <T> T getBean(String name, Class<T> requiredType) throws BeansException{
		return getContext().getBean(name, requiredType);
	}

	private static void checkApplicationContext() {
		Assert.notNull(springContext, "ApplicationContext未注入，"
				+ SpringContextHolder.class + "未配置到spring环境中");
	}

	public static ApplicationContext getContext() {
		checkApplicationContext();
		return springContext;
	}
}