package com.thomsonreuters.testMybatis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thomsonreuters.common.web.bean.ResultBean;
import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-persistence.xml" })
public class TestMybatis {
	private static Logger logger = Logger.getLogger(TestMybatis.class);
	// private ApplicationContext ac = null;
	@Resource
	private IUserService userService = null;

	// @Before
	// public void before() {
	// ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// userService = (IUserService) ac.getBean("userService");
	// }
	

	@Test
	public void test1() {
//		User user = userService.findByUsername("admin");
//		System.err.println(user.getUsername());
//		// logger.info("值："+user.getUsername());
//		// logger.info(JSON.toJSONString(user));
//		logger.info(user.getUsername());
//		logger.info(user.getPassword());
	}
	
	@Test
	public void testUserSave() {
		User user = userService.get(1);
//		User user = userService.getByUsername("admin");
		logger.info(user);
	}
}
