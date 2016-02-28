package com.thomsonreuters.modules.am.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thomsonreuters.common.config.GlobalConstants;
import com.thomsonreuters.common.service.BaseService;
import com.thomsonreuters.common.utils.StringUtils;
import com.thomsonreuters.common.web.bean.ResultBean;
import com.thomsonreuters.modules.am.IDao.UserDao;
import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.service.IUserService;
import com.thomsonreuters.modules.am.service.PasswordHelper;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseService implements IUserService {
	@Resource
	private UserDao userDao;

	@Resource
	private PasswordHelper passwordHelper;

	@Override
	public User get(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.get(userId);
	}

	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.getByUsername(username);
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
	public List<User> findList(String username) {
		// TODO Auto-generated method stub
		return userDao.findUsers(username);
	}

	@Override
	@Transactional(readOnly = false)
	public ResultBean delete(Integer userId) {
		// TODO Auto-generated method stub
		try {
			userDao.delete(userId);
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
			return new ResultBean();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResultBean(GlobalConstants.FAILURE, e.getCause()
					.getMessage());
		}
	}

}
