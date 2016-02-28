package com.thomsonreuters.modules.am.service;

import java.util.List;
import java.util.Set;

import com.thomsonreuters.common.web.bean.ResultBean;
import com.thomsonreuters.modules.am.domain.User;

public interface IUserService {
	
	/**
     * get
     * @param userId
     * @return
     */
	public User get(Integer userId); 
	
	/**
     * getByUsername
     * @param username
     * @return
     */
    public User getByUsername(String username);

    /**
     * findRoles
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * findPermissions
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);
    
    /**
     * findList
     * @param username
     * @return
     */
    public List<User> findList(String username);
    
    /**
     * delete
     * @param userId
     * @return
     */
    public ResultBean delete(Integer userId);
    
    /**
     * save
     * @param user
     * @return
     */
    public ResultBean save(User user);
    
}
