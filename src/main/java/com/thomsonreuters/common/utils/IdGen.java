package com.thomsonreuters.common.utils;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.UUID;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * IdGenerator
 * 
 * @author
 * @version
 */
@Service
@Lazy(false)
public class IdGen implements SessionIdGenerator {

	private static SecureRandom random = new SecureRandom();

	/**
	 * uuid
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * randomLong
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * randomBase62
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}

	@Override
	public Serializable generateId(Session session) {
		return IdGen.uuid();
	}

}
