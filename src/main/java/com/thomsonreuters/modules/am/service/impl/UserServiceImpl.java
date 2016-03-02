package com.thomsonreuters.modules.am.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thomsonreuters.common.annotation.SystemServiceLog;
import com.thomsonreuters.common.config.GlobalConstants;
import com.thomsonreuters.common.service.BaseService;
import com.thomsonreuters.common.utils.StringUtils;
import com.thomsonreuters.common.web.bean.ResultBean;
import com.thomsonreuters.modules.am.IDao.UserDao;
import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.service.IUserService;
import com.thomsonreuters.modules.am.service.PasswordHelper;
import com.thomsonreuters.modules.am.utils.UserUtils;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseService implements IUserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordHelper passwordHelper;

	@Override
	@SystemServiceLog(description = "UserServiceImpl - get(userId)")
	public User get(Integer userId) {
		// TODO Auto-generated method stub
		return UserUtils.get(userId);
	}

	@Override
	@SystemServiceLog(description = "UserServiceImpl - getByUsername(cusername)")
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return UserUtils.getByUsername(username);
	}

	@Override
	public Set<String> findRoles(String username) {
		// TODO Auto-generated method stub
		List<String> l = userDao.findRoles(username);
		Set<String> set = new HashSet<String>();
		set.addAll(l);
		return set;
	}

	@Override
	public Set<String> findPermissions(String username) {
		// TODO Auto-generated method stub
		List<String> l = userDao.findPermissions(username);
		Set<String> set = new HashSet<String>();
		set.addAll(l);
		return set;
	}

	@Override
	public List<User> findList(String searchCriteria) {
		// TODO Auto-generated method stub
		return userDao.findUsers(searchCriteria);
	}

	@Override
	@SystemServiceLog(description = "UserServiceImpl - findList(currentPage, displayLength, searchCriteria)")
	public PageInfo<User> findList(Integer currentPage, Integer displayLength,
			String searchCriteria) {
		// TODO Auto-generated method stub
		PageHelper.startPage(currentPage, displayLength);
		PageInfo<User> page = new PageInfo<User>(
				userDao.findUsers(searchCriteria));
		return page;
	}

	@Override
	@Transactional(readOnly = false)
	@SystemServiceLog(description = "UserServiceImpl - delete(user)")
	public ResultBean delete(User user) {
		// TODO Auto-generated method stub
		try {
			userDao.delete(user);
			if (user.getId() != null) {
				userDao.deleteUserRole(user);
			}
			UserUtils.clearCache(user);
			return new ResultBean();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResultBean(GlobalConstants.FAILURE, e.getCause()
					.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = false)
	@SystemServiceLog(description = "UserServiceImpl - save(user)")
	public ResultBean save(User user) {
		// TODO Auto-generated method stub
		try {
			if (user.getId() != null) {
				user.preUpdate();
				userDao.update(user);
			} else {
				user.preInsert();
				userDao.insert(user);
			}
			if (user.getId() != null && user.getRole() != null) {
				if (StringUtils.isNotBlank(user.getRole().getRole())) {
					userDao.deleteUserRole(user);
					userDao.insertUserRole(user);
				}
			}
			UserUtils.clearCache(user);
			return new ResultBean();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResultBean(GlobalConstants.FAILURE, e.getCause()
					.getMessage());
		}
	}

}
