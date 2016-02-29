package com.thomsonreuters.modules.am.IDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thomsonreuters.common.persistence.CrudDao;
import com.thomsonreuters.common.persistence.annotation.MyBatisDao;
import com.thomsonreuters.modules.am.domain.User;

@MyBatisDao
public interface UserDao extends CrudDao<User>{
    
	User get(Integer id);
   
    User getByUsername(String username);

    List<String> findRoles(String username);

    List<String> findPermissions(String username);
    
    List<User> findUsers(@Param("username") String username);
    
    int insertUserRole(User record);
    
    int deleteUserRole(User record);
}