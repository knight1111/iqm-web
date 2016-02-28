package com.thomsonreuters.modules.am.IDao;

import com.thomsonreuters.common.persistence.annotation.MyBatisDao;
import com.thomsonreuters.modules.am.domain.Role;

@MyBatisDao
public interface RoleDao {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}