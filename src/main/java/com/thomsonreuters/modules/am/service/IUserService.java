package com.thomsonreuters.modules.am.service;

import java.util.List;
import java.util.Set;

import com.thomsonreuters.modules.am.domain.User;

public interface IUserService {
	public User findUserById(int userId); 
	
	/**
     * findByUsername
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * findRolesByUsername
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * findPermissionsByUsername
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);
    
    /**
     * findUsers
     * @param username
     * @return
     */
    public List<User> findUsers(String username);
    
    /**
     * delete
     * @param userId
     * @return
     */
    public int delete(int userId);
    
    /**
     * save
     * @param user
     * @return
     */
    public int save(User user);
    
}
