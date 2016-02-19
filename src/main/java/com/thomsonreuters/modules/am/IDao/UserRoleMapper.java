package com.thomsonreuters.modules.am.IDao;

import com.thomsonreuters.modules.am.domain.UserRoleKey;

public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);
}