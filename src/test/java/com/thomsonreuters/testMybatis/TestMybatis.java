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

	@Resource
	private IUserService userService;

	@Test
	public void test() {
		User user = userService.getByUsername("admin");
		logger.info(user.getUsername());
		logger.info(user.getName());
	}

	@Test
	public void testUserSave() {
		User user = userService.getByUsername("222");
		if (user != null) {
			user.setName("NewTwo");
			logger.info(userService.save(user).getIsSuccess());
		}
	}

//	@Test
//	public void testUserDelete() {
//		User user = userService.getByUsername("111");
//		if (user != null) {
//			logger.info(userService.delete(user).getIsSuccess());
//		}
//	}
}
