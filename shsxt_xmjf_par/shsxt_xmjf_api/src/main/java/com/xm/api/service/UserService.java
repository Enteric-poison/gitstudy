package com.xm.api.service;

import com.xm.api.po.BasUser;
import com.xm.api.querys.BasUserQuery;

import java.util.List;

/**
 * @handsome
 * @date 2018/9/9 15:09
 */
public interface UserService{
    public BasUser queryUserByName(String userName);
    public BasUser queryByPhone(String mobile);
}
