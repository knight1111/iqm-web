package com.thomsonreuters.modules.am.realm;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.service.IUserService;
import com.thomsonreuters.modules.am.utils.UserUtils;

public class UserRealm extends AuthorizingRealm {
	@Resource
	private IUserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = ((Principal) principals.getPrimaryPrincipal()).getUsername();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findRoles(username));
		authorizationInfo.setStringPermissions(userService.findPermissions(username));

		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String username = (String) token.getPrincipal();

		User user = userService.getByUsername(username);
		if (user == null) {
			throw new UnknownAccountException();// UnknownAccountException
		}
		if (Boolean.TRUE.equals(user.getLocked())) {
			throw new LockedAccountException(); // LockedAccountException
		}

		// AuthenticatingRealm, CredentialsMatcher -> password match
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(new Principal(user),
				user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), // salt
																						// =
																						// username+salt
				getName() // RealmName
		);
		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}
	
	/**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private Integer id; // 编号
		private String username; // 登录名
		private String name; // 姓名

		public Principal(User user) {
			this.id = user.getId();
			this.username = user.getUsername();
			this.name = null;//user.getName();
		}

		public Integer getId() {
			return id;
		}

		public String getUsername() {
			return username;
		}

		public String getName() {
			return name;
		}

//		@JsonIgnore
//		public Map<String, Object> getCacheMap() {
//			if (cacheMap==null){
//				cacheMap = new HashMap<String, Object>();
//			}
//			return cacheMap;
//		}

		/**
		 * 获取SESSIONID
		 */
		public String getSessionid() {
			try{
				return (String) UserUtils.getSession().getId();
			}catch (Exception e) {
				return "";
			}
		}
		
		@Override
		public String toString() {
			return username;
		}

	}

}
