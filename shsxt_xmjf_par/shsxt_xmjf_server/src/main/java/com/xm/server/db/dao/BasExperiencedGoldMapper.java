package com.xm.server.db.dao;

import com.xm.api.po.BasExperiencedGold;

public interface BasExperiencedGoldMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasExperiencedGold record);

    int insertSelective(BasExperiencedGold record);

    BasExperiencedGold selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasExperiencedGold record);

    int updateByPrimaryKey(BasExperiencedGold record);
}