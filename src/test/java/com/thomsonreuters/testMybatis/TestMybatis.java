package com.thomsonreuters.testMybatis;

import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
	
	private static Object invokeMethod(String className, String methodName,
			Object[] args) throws Exception {

		Class ownerClass = Class.forName(className);
		Object owner = ownerClass.newInstance();
		Class[] argsClass = new Class[args.length];

		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Method method = ownerClass.getMethod(methodName, argsClass);
		return method.invoke(owner, args);
	}

	@Test
	public void test1() {
//		User user = userService.findByUsername("admin");
//		System.err.println(user.getUsername());
//		// logger.info("值："+user.getUsername());
//		// logger.info(JSON.toJSONString(user));
//		logger.info(user.getUsername());
//		logger.info(user.getPassword());
		
		String iDisplayStart = "1";
		String iDisplayLength = "5";
		String sSearch = "est3";
//		
//		List l = userService.findUsers(sSearch);
//		System.err.println(l.size());
		
		String text = "[{\"name\":\"sEcho\",\"value\":3},{\"name\":\"iColumns\",\"value\":5}]";
		System.err.println(StringEscapeUtils.escapeHtml4(text.trim()));
		
//		PageHelper.startPage(Integer.valueOf(iDisplayStart),
//				Integer.valueOf(iDisplayLength));
//		List l = userService.findUsers(sSearch);
//		System.err.println(l.size());
//		
//		PageInfo<User> page = new PageInfo<User>(l);
//
//		System.err.println(page);
//		System.err.println(page.getList()
//				.size());

	}
}
