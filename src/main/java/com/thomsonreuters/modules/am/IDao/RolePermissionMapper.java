package com.thomsonreuters.modules.am.IDao;

import com.thomsonreuters.modules.am.domain.RolePermissionKey;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(RolePermissionKey key);

    int insert(RolePermissionKey record);

    int insertSelective(RolePermissionKey record);
}