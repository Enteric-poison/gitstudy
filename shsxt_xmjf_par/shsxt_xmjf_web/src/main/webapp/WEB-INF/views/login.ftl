<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <#include "include/common.ftl">
    <link rel="stylesheet" href="${ctx}/statics/css/login.css">
    <script type="text/javascript" src="${ctx}/statics/js/login.js"></script>
</head>
<body>
    <#include "include/header.ftl">
<div class="login-content">
    <div class="login-content-center clear">
        <div class="whole-img">
            <img src="/statics/img/loginBackground.png"/>
        </div>
        <div class="login-box">
            <div class="box-title">
                用户登录
            </div>
            <div class="box-input">
                <div class="box-input-center">
                    <p class="login_input_wrap">
                        <input id="phone" placeholder="手机号" type="text" class="login-input"/>
                    </p>
                    <p class="login_input_password">
                        <input id="password" placeholder="请输入密码" type="password" class="login-input"/>
                    </p>
                </div>
                <div class="forget-passwrod">
                    <div class="forget-passwrod-center">
                        <a href="${ctx}/forgetPassword" style="cursor: pointer;color: #ff5e5e;float: left;font-size: 16px">忘记密码？</a>
                        <a href="${ctx}/quickLogin" style="cursor: pointer;color: #606060;float: right;font-size: 16px">
                            快捷登录
                        </a>
                    </div>
                </div>
                <div class="login-but">
                    <div class="login-but-center">
                        <button id="login" class="but">
                            登录
                        </button>
                    </div>
                </div>
                <div class="function">
                    <div class="function-center">
                        <div class="function-register">
                            没有账号？<span><a style="color: #ff5e5e;cursor:pointer;" href="/register">免费注册</a></span>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>