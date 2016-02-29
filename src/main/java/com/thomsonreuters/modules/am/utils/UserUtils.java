package com.thomsonreuters.modules.am.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.thomsonreuters.common.utils.CacheUtils;
import com.thomsonreuters.common.utils.SpringContextHolder;
import com.thomsonreuters.modules.am.IDao.RoleDao;
import com.thomsonreuters.modules.am.IDao.UserDao;
import com.thomsonreuters.modules.am.domain.Role;
import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.realm.UserRealm.Principal;

/**
 * UserUtils
 * 
 * @author
 * @version
 */
public class UserUtils {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);

	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";

	public static final String CACHE_ROLE_LIST = "roleList";

	/**
	 * getUserById
	 * 
	 * @param id
	 * @return
	 */
	public static User get(Integer id) {
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user == null) {
			user = userDao.get(id);
			if (user == null) {
				return null;
			}
			// user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE,
					USER_CACHE_LOGIN_NAME_ + user.getUsername(), user);
		}
		return user;
	}

	/**
	 * getUserByLoginName
	 * 
	 * @param loginName
	 * @return
	 */
	public static User getByUsername(String username) {
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_
				+ username);
		if (user == null) {
			user = userDao.getByUsername(username);
			if (user == null) {
				return null;
			}
			// user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE,
					USER_CACHE_LOGIN_NAME_ + user.getUsername(), user);
		}
		return user;
	}

	/**
	 * clearCache
	 */
	public static void clearCache() {
		removeCache(CACHE_ROLE_LIST);
		UserUtils.clearCache(getUser());
	}

	/**
	 * clearCache
	 * 
	 * @param user
	 */
	public static void clearCache(User user) {
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		CacheUtils.remove(USER_CACHE,
				USER_CACHE_LOGIN_NAME_ + user.getUsername());
	}

	/**
	 * getCurrentUser
	 * 
	 * @return
	 */
	public static User getUser() {
		Principal principal = getPrincipal();
		if (principal != null) {
			User user = get(principal.getId());
			if (user != null) {
				return user;
			}
			return new User();
		}
		return new User();
	}

	/**
	 * getRoleList
	 * 
	 * @return
	 */
	// public static List<Role> getRoleList() {
	// @SuppressWarnings("unchecked")
	// List<Role> roleList = (List<Role>) getCache(CACHE_ROLE_LIST);
	// if (roleList == null) {
	// User user = getUser();
	// if (user.isAdmin()) {
	// roleList = roleDao.findAllList(new Role());
	// } else {
	// Role role = new Role();
	// role.getSqlMap().put(
	// "dsf",
	// BaseService.dataScopeFilter(user.getCurrentUser(), "o",
	// "u"));
	// roleList = roleDao.findList(role);
	// }
	// putCache(CACHE_ROLE_LIST, roleList);
	// }
	// return roleList;
	// }

	/**
	 * getSubject
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * getPrincipal
	 */
	public static Principal getPrincipal() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
		} catch (UnavailableSecurityManagerException e) {

		} catch (InvalidSessionException e) {

		}
		return null;
	}

	/**
	 * getSession
	 */
	public static Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}
		} catch (InvalidSessionException e) {

		}
		return null;
	}

	// ============== User Cache ==============

	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
		// Object obj = getCacheMap().get(key);
		Object obj = getSession().getAttribute(key);
		return obj == null ? defaultValue : obj;
	}

	public static void putCache(String key, Object value) {
		// getCacheMap().put(key, value);
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
		// getCacheMap().remove(key);
		getSession().removeAttribute(key);
	}

	// public static Map<String, Object> getCacheMap(){
	// Principal principal = getPrincipal();
	// if(principal!=null){
	// return principal.getCacheMap();
	// }
	// return new HashMap<String, Object>();
	// }

}
