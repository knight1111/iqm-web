package com.thomsonreuters.common.utils;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Static Spring ApplicationContext
 * 
 * @author
 * @date
 */
@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware,
		DisposableBean {

	private static ApplicationContext applicationContext = null;

	private static Logger logger = LoggerFactory
			.getLogger(SpringContextHolder.class);

	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}

	/**
	 * getBean
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * getBean
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	/**
	 * clearHolder
	 */
	public static void clearHolder() {
		if (logger.isDebugEnabled()) {
			logger.debug("Clear SpringContextHolder in ApplicationContext:"
					+ applicationContext);
		}
		applicationContext = null;
	}

	/**
	 * setApplicationContext
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextHolder.applicationContext = applicationContext;
	}

	/**
	 * implement interface DisposableBean
	 */
	@Override
	public void destroy() throws Exception {
		SpringContextHolder.clearHolder();
	}

	/**
	 * assertContextInjected
	 */
	private static void assertContextInjected() {
		Validate.validState(applicationContext != null,
				"SpringContextHolder not registered.");
	}
}