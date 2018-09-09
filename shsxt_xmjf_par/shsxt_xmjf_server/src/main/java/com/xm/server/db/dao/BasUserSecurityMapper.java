package com.xm.server.db.dao;

import com.xm.api.po.BasUserSecurity;

public interface BasUserSecurityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasUserSecurity record);

    int insertSelective(BasUserSecurity record);

    BasUserSecurity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasUserSecurity record);

    int updateByPrimaryKey(BasUserSecurity record);
}