package com.thomsonreuters.testMybatis;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring-persistence.xml" })
public class TestMybatis {
	private static Logger logger = Logger.getLogger(TestMybatis.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	private MockHttpServletRequest request;

	@Before
	public void before() {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(
				request));
	}

	@Test
	public void test() {
		User user = userService.getByUsername("admin");
		logger.info(user.getUsername());
		logger.info(user.getName());
	}

	@Test
	public void testUserSave() {
		User user = userService.getByUsername("test1");
		if (user != null) {
			user.setName("NewTwo");
			logger.info(userService.save(user).getIsSuccess());
		}
	}

	// @Test
	// public void testUserDelete() {
	// User user = userService.getByUsername("111");
	// if (user != null) {
	// logger.info(userService.delete(user).getIsSuccess());
	// }
	// }
}
