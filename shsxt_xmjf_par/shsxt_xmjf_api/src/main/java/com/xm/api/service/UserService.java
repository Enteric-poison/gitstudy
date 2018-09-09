package com.xm.api.service;

import com.xm.api.po.BasUser;

/**
 * @handsome
 * @date 2018/9/9 15:09
 */
public interface UserService {
    public BasUser queryUserByName(String userName);
}
