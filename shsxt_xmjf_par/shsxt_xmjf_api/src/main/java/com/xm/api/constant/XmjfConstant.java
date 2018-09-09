package com.xm.api.constant;

/**
 * @handsome
 * @date 2018/9/9 15:04
 */
public class XmjfConstant {
    //前台判断常量
    public static final Integer SUCCESS_CODE=200;//成功
    public static final String SUCCESS_MSG="success";

    public static final Integer FAILED_CODE=300;
    public static final String FAILED_MSG="failed";

    // 图片验证码常量串
    public static final String IMAGE_VERIFY="image";

    // 手机短信类型常亮
    public static final Integer SMS_LOGIN_TYPE=1;//登录
    public static final Integer SMS_REGISTER_TYPE=2;//注册
    public static final Integer SMS_REGISTER_SUCCESS_NOYIRY_TYPE=3;//注册成功
    public static final String SMS_LOGIN_TEMPLATE_CODE="SMS_144145031";//登录码
    public static final String SMS_REGISTER_TEMPLATE_CODE="SMS_144145126";//注册码
    public static final String  SMS_NOTIFY__TEMPLATE_CDOE="SMS_144152152";//通知码

    //验证码时间
    public static final  Integer SMS_EXPIRE_TIME=180;

}
