package com.thomsonreuters.modules.am.service;

import java.util.List;
import java.util.Set;

import com.thomsonreuters.modules.am.domain.User;

public interface IUserService {
	public User getUserById(int userId); 
	
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
}
