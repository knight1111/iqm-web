package com.thomsonreuters.modules.am.service;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import com.thomsonreuters.modules.am.domain.User;

@Component
public class PasswordHelper {

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";
	private int hashIterations = 2;

	public void setRandomNumberGenerator(
			RandomNumberGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	public void encryptPassword(User user) {

		// user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String password = encryptPassword(user.getPassword(),
				user.getCredentialsSalt());

		user.setPassword(password);
	}

	public String encryptPassword(String password, String credentialsSalt) {
		if (password == null) {
			return null;
		}
		return new SimpleHash(algorithmName, password,
				ByteSource.Util.bytes(credentialsSalt), hashIterations).toHex();
	}
}
