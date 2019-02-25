package com.duobei.context;


import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

@Component("ApiCoreAppContext")
public class AppContext implements ApplicationContextAware {
	private static Logger log = LoggerFactory.getLogger(AppContext.class);

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		context = ctx;
		log.info("ApiCoreAppContext injection completed.");
		log.info("初始化发号器-开始");
		log.info("初始化发号器-结束");
	}

	public static ApplicationContext getAppContext() {
		return context;
	}
	
	public static ServletContext getServletContext() {
		return ContextLoader.getCurrentWebApplicationContext().getServletContext();
	}
	public static Object getBean(String name) {
		return context.getBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}
	
}
