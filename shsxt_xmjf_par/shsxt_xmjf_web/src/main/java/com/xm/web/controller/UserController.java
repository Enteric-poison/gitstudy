package com.xm.web.controller;

import com.xm.api.model.ResultInfo;
import com.xm.api.po.BasUser;
import com.xm.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @handsome
 * @date 2018/9/9 15:21
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;

    //通过用户名查询
    @RequestMapping("user/queryByName")
    @ResponseBody
    public BasUser queryUserByName(String userName){
        System.out.println("用户记录查询....");
        return userService.queryUserByName(userName);
    }

    //注册用户
    @RequestMapping("user/doRegister")
    @ResponseBody
    public ResultInfo doSave(String mobile,String code,String password){
        userService.saveUser(mobile,password,code);
        return new ResultInfo();
    }



}
