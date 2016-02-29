package com.thomsonreuters.modules.am.service;

import java.util.List;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.thomsonreuters.common.web.bean.ResultBean;
import com.thomsonreuters.modules.am.domain.User;

public interface IUserService {

	/**
	 * get
	 * 
	 * @param userId
	 * @return
	 */
	public User get(Integer userId);

	/**
	 * getByUsername
	 * 
	 * @param username
	 * @return
	 */
	public User getByUsername(String username);

	/**
	 * findRoles
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findRoles(String username);

	/**
	 * findPermissions
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findPermissions(String username);

	/**
	 * findList
	 * 
	 * @param searchCriteria
	 * @return
	 */
	public List<User> findList(String searchCriteria);
	
	/**
	 * findList
	 * 
	 * @param currentPage
	 * @param displayLength
	 * @param searchCriteria
	 * @return
	 */
	public PageInfo<User> findList(Integer currentPage, Integer displayLength,
			String searchCriteria);

	/**
	 * delete
	 * 
	 * @param user
	 * @return
	 */
	public ResultBean delete(User user);

	/**
	 * save
	 * 
	 * @param user
	 * @return
	 */
	public ResultBean save(User user);

}
