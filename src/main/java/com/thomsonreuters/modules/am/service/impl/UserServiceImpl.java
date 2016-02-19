package com.thomsonreuters.modules.am.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thomsonreuters.modules.am.IDao.UserDao;
import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.service.IUserService;
import com.thomsonreuters.modules.am.service.PasswordHelper;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements IUserService {
	@Resource
	private UserDao userDao;
	
	@Resource
	private PasswordHelper passwordHelper;

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

	@Override
	public Set<String> findRoles(String username) {
		// TODO Auto-generated method stub
		List<String> l = userDao.findRoles(username);
	    Set<String> set=new HashSet<String>();         
        set.addAll(l);
		return set;
	}

	@Override
	public Set<String> findPermissions(String username) {
		// TODO Auto-generated method stub
		List<String> l = userDao.findPermissions(username);
	    Set<String> set=new HashSet<String>();         
        set.addAll(l);
		return set;
	}

	@Override
	public List<User> findUsers(String username) {
		// TODO Auto-generated method stub
		return userDao.findUsers(username);
	}

}
