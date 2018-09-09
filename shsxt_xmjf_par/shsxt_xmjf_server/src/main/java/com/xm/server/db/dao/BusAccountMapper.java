package com.xm.server.db.dao;

import com.xm.api.po.BusAccount;

public interface BusAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusAccount record);

    int insertSelective(BusAccount record);

    BusAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusAccount record);

    int updateByPrimaryKey(BusAccount record);
}