package com.thomsonreuters.modules.am.realm;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.realm.UserRealm.Principal;
import com.thomsonreuters.modules.am.service.IUserService;

public class MyCasRealm extends CasRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IUserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// String username = (String) principals.getPrimaryPrincipal();
		Principal p = (Principal) principals.getPrimaryPrincipal();
		String username = p.getUsername();
		SecurityUtils
				.getSubject()
				.getSession()
				.setAttribute(username,
						SecurityUtils.getSubject().getPrincipals());

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findRoles(username));
		authorizationInfo.setStringPermissions(userService
				.findPermissions(username));

		return authorizationInfo;
	}

	/**
	 * Authenticates a user and retrieves its information.
	 * 
	 * @param token
	 *            the authentication token
	 * @throws AuthenticationException
	 *             if there is an error during authentication.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		CasToken casToken = (CasToken) token;
		if (token == null) {
			return null;
		}

		String ticket = (String) casToken.getCredentials();
		if (!StringUtils.hasText(ticket)) {
			return null;
		}

		TicketValidator ticketValidator = ensureTicketValidator();

		try {
			// contact CAS server to validate service ticket
			Assertion casAssertion = ticketValidator.validate(ticket,
					getCasService());
			// get principal, user id and attributes
			AttributePrincipal casPrincipal = casAssertion.getPrincipal();
			String userId = casPrincipal.getName();
			logger.debug(
					"Validate ticket : {} in CAS server : {} to retrieve user : {}",
					new Object[] { ticket, getCasServerUrlPrefix(), userId });

			Map<String, Object> attributes = casPrincipal.getAttributes();
			// refresh authentication token (user id + remember me)
			casToken.setUserId(userId);
			String rememberMeAttributeName = getRememberMeAttributeName();
			String rememberMeStringValue = (String) attributes
					.get(rememberMeAttributeName);
			boolean isRemembered = rememberMeStringValue != null
					&& Boolean.parseBoolean(rememberMeStringValue);
			if (isRemembered) {
				casToken.setRememberMe(true);
			}

			// create simple authentication info
			// get userInfo
			User user = userService.getByUsername(userId);
			if (user == null) {
				throw new UnknownAccountException();// UnknownAccountException
			}
			if (Boolean.TRUE.equals(user.getLocked())) {
				throw new LockedAccountException(); // LockedAccountException
			}

			// AuthenticatingRealm, CredentialsMatcher -> password match
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
					new Principal(user), ticket, getName() // RealmName
			);

			return authenticationInfo;
		} catch (TicketValidationException e) {
			throw new CasAuthenticationException("Unable to validate ticket ["
					+ ticket + "]", e);
		}
	}

}
