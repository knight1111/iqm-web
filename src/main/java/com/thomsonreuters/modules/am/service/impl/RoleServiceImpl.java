package com.thomsonreuters.modules.am.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thomsonreuters.common.config.GlobalConstants;
import com.thomsonreuters.common.service.BaseService;
import com.thomsonreuters.common.web.bean.ResultBean;
import com.thomsonreuters.modules.am.IDao.RoleDao;
import com.thomsonreuters.modules.am.domain.Role;
import com.thomsonreuters.modules.am.service.IRoleService;

@Service("roleService")
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseService implements IRoleService {
	@Autowired
	private RoleDao roleDao;

	@Override
	public Role get(Integer roleId) {
		// TODO Auto-generated method stub
		return roleDao.get(roleId);
	}

	@Override
	public Role getByName(String roleName) {
		// TODO Auto-generated method stub
		return roleDao.getByName(roleName);
	}

	@Override
	public List<Role> findList() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public ResultBean delete(Role role) {
		// TODO Auto-generated method stub
		try {
			roleDao.delete(role);
			if (role.getId() != null) {
				roleDao.deleteUserRole(role);
			}
			// UserUtils.clearCache(user);
			return new ResultBean();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResultBean(GlobalConstants.FAILURE, e.getCause().getMessage());
		}
	}

	@Override
	@Transactional(readOnly = false)
	public ResultBean save(Role role) {
		// TODO Auto-generated method stub
		try {
			if (role.getId() != null) {
				role.preUpdate();
				roleDao.update(role);
			} else {
				role.preInsert();
				roleDao.insert(role);
			}
			// UserUtils.clearCache(user);
			return new ResultBean();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResultBean(GlobalConstants.FAILURE, e.getCause().getMessage());
		}
	}

}
