package com.weixin.publicnation.utils;

import org.springframework.context.ApplicationContext;

public class SpringContextUtils {
	private static ApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}

	public static Object getBean(String beanId) {
		return applicationContext.getBean(beanId);
	}
	
	public static <T> T getBean(Class<T> t){
		return applicationContext.getBean(t);
	}
}