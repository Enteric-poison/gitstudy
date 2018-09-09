package com.xm.server.db.dao;

import com.xm.api.po.BasUser;
import com.xm.server.db.base.BaseDao;

public interface BasUserMapper extends BaseDao<BasUser> {
    public BasUser queryUserByName(String userName);
    int deleteByPrimaryKey(Integer id);

    int insert(BasUser record);

    int insertSelective(BasUser record);

    BasUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasUser record);

    int updateByPrimaryKey(BasUser record);
}