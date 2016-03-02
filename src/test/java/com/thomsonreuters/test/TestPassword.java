package com.thomsonreuters.test;

import org.junit.Test;

import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.service.PasswordHelper;

public class TestPassword {
	
	@Test
	public void test1() {
		
		PasswordHelper passwordHelper = new PasswordHelper();
		
		String username = "admin";
		
		User u = new User(-1, username);
		u.setPassword("123456");
		u.setSalt("1");
		passwordHelper.encryptPassword(u);
		
		System.err.println("------------------->"+u.getPassword());
	}

}
