package com.xm.server.db.dao;

import com.xm.api.po.BasExperiencedGold;
import com.xm.server.db.base.BaseDao;

public interface BasExperiencedGoldMapper extends BaseDao<BasExperiencedGold> {
    int deleteByPrimaryKey(Integer id);

    int insert(BasExperiencedGold record);

    int insertSelective(BasExperiencedGold record);

    BasExperiencedGold selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasExperiencedGold record);

    int updateByPrimaryKey(BasExperiencedGold record);
}