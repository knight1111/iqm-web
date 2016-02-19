package com.thomsonreuters.common.persistence.jdbc.jdbcBaseDao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcTempBaseDao extends JdbcDaoSupport {

	/**
   */
	@Resource(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void initSqlMapClient() {
		super.setJdbcTemplate(jdbcTemplate);
	}
}
