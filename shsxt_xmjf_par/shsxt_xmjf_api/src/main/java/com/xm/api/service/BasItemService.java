package com.xm.api.service;

import com.xm.api.querys.BasItemQuery;
import com.xm.api.util.PageList;

/**
 * @handsome
 * @date 2018/9/10 20:15
 */
public interface BasItemService {
    public PageList queryBasItemsByParams(BasItemQuery basItemQuery);

    public void updateStatus(Integer itemId);
}
