package com.thomsonreuters.modules.am.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.thomsonreuters.common.web.bean.ResultBean;
import com.thomsonreuters.modules.am.domain.Role;

public interface IRoleService {
	
	/**
	 * get
	 * 
	 * @param roleId
	 * @return
	 */
	public Role get(Integer roleId);

	/**
	 * getByName
	 * 
	 * @param roleName
	 * @return
	 */
	public Role getByName(String roleName);

	/**
	 * findList
	 * 
	 * @return
	 */  
	public List<Role> findList();
	
	/**
	 * delete
	 * 
	 * @param role
	 * @return
	 */
	public ResultBean delete(Role role);

	/**
	 * save
	 * 
	 * @param role
	 * @return
	 */
	public ResultBean save(Role role);

}
