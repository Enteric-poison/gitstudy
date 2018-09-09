package com.xm.server.db.dao;

import com.xm.api.po.BusUserIntegral;

public interface BusUserIntegralMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusUserIntegral record);

    int insertSelective(BusUserIntegral record);

    BusUserIntegral selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusUserIntegral record);

    int updateByPrimaryKey(BusUserIntegral record);
}