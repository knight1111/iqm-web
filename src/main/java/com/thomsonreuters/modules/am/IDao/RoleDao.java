package com.thomsonreuters.modules.am.IDao;

import com.thomsonreuters.common.persistence.CrudDao;
import com.thomsonreuters.common.persistence.annotation.MyBatisDao;
import com.thomsonreuters.modules.am.domain.Role;

@MyBatisDao
public interface RoleDao extends CrudDao<Role> {

    Role get(Integer id);
    
    Role getByName(String roleName);
    
    int deleteUserRole(Role role);
}