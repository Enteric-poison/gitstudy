package com.xm.web.controller;

import com.xm.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @handsome
 * @date 2018/9/9 16:03
 */
//请求转发页面
@Controller
public class IndexController extends BaseController {
    //主页
    @RequestMapping("index")
    public String index(){
        return "index";
    }
    //登录
    @RequestMapping("login")
    public String login(){
        return "login";
    }
    //注册
    @RequestMapping("register")
    public String register(){
        return "register";
    }
}
