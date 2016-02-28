package com.thomsonreuters.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * BaseService
 * @author ThinkGem
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class BaseService {
	
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

}
