package com.thomsonreuters.modules.am.IDao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.thomsonreuters.modules.am.domain.User;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User findByUsername(String username);

    List<String> findRoles(String username);

    List<String> findPermissions(String username);
    
    List<User> findUsers(@Param("username") String username);
}