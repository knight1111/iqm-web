package com.thomsonreuters.test;

import org.junit.Test;

import com.thomsonreuters.common.mapper.JsonMapper;
import com.thomsonreuters.modules.am.domain.User;

public class TestJson {

	@Test
	public void test(){
		User u = new User(1, "User1");
		u.setName("Kelly");
		u.setLocked(0);
		u.setSalt("1");
		String json = JsonMapper.toJsonString(u);
		System.err.println(json);
		
		User u1 = (User)JsonMapper.fromJsonString(json, User.class);
		System.err.println(u1);
	}
}
