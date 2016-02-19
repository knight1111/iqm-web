package com.thomsonreuters.test;

import org.junit.Test;

import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.service.PasswordHelper;

public class TestPassword {
	@Test
	public void test1() {
		
		String username = "test2";
		
		PasswordHelper ph = new PasswordHelper();
		User u = new User();
		u.setUsername(username);
		u.setPassword(username);
		u.setSalt("1");
		ph.encryptPassword(u);
		
		System.out.println(u.getPassword());
	}
}
