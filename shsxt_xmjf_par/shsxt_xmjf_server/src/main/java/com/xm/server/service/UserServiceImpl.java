package com.xm.server.service;

import com.xm.api.po.BasUser;
import com.xm.api.querys.BasUserQuery;
import com.xm.api.service.UserService;
import com.xm.server.db.dao.BasUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @handsome
 * @date 2018/9/9 15:34
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private BasUserMapper basUserMapper;
    @Override
    public BasUser queryUserByName(String userName) {
        return basUserMapper.queryUserByName(userName);
    }

    @Override
    public BasUser queryByPhone(String mobile) {
        return basUserMapper.queryByPhone(mobile);
    }


}
