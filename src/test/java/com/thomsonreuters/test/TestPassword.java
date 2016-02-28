package com.thomsonreuters.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.service.PasswordHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-persistence.xml" })
public class TestPassword {
	
	@Resource
	private PasswordHelper passwordHelper;
	
	@Test
	public void test1() {
		
		String username = "admin";
		
		User u = new User(-1, username);
		u.setPassword(null);
		u.setSalt("1");
		passwordHelper.encryptPassword(u);
		
		System.err.println("------------------->"+u.getPassword());
		
		username = null;
		System.err.println("------------------->"+"55555".equals(username));
	}

}
