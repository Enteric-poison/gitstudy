package com.xm.web.controller;

import com.xm.api.constant.XmjfConstant;
import com.xm.api.exceptions.BusiException;
import com.xm.api.model.ResultInfo;
import com.xm.api.model.UserModel;
import com.xm.api.po.BasUser;
import com.xm.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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

    //密码登录
    @ResponseBody
    @RequestMapping("user/login")
    public ResultInfo login(String mobile, String password, HttpSession session){
        ResultInfo resultInfo=new ResultInfo();
        try {
            UserModel userModel= userService.userLogin(mobile,password);
            session.setAttribute(XmjfConstant.USER_INFO,userModel);
        } catch (BusiException e) {
            e.printStackTrace();
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(XmjfConstant.FAILED_CODE);
            resultInfo.setMsg(XmjfConstant.FAILED_MSG);
        }

        return resultInfo;
    }
    //快速登录
    @RequestMapping("doquickLogin")
    @ResponseBody
    public ResultInfo quickLogin(String mobile, String code, HttpSession session){
        UserModel userModel= userService.quickLogin(mobile,code);
        session.setAttribute(XmjfConstant.USER_INFO,userModel);
        return new ResultInfo();
    }


}
