package com.duobei.context;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("AppContext")
public class AppContext implements ApplicationContextAware {

	private static ApplicationContext context;
	private static Logger log = LoggerFactory.getLogger(AppContext.class);

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		context = ctx;
		log.info("AppContext injection completed.");
	}

	public static ApplicationContext getAppContext() {
		return context;
	}
	public static Object getBean(String name) {
		return context.getBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}
	
	public static <T> Map<String, T> getBeansOfType(Class<T> type) {
		return context.getBeansOfType(type);
	}
	
}
