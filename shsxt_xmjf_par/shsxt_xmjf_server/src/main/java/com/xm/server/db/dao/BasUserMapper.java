package com.xm.server.db.dao;

import com.xm.api.po.BasUser;
import com.xm.server.db.base.BaseDao;

public interface BasUserMapper extends BaseDao<BasUser> {
    public BasUser queryUserByName(String userName);
    public BasUser queryByPhone(String mobile);
}