package com.xm.api.exceptions;

import com.xm.api.constant.XmjfConstant;

/**
 * @handsome
 * @date 2018/9/9 15:07
 */

//登录拦截
public class NoLoginException extends RuntimeException{

    private  Integer code= XmjfConstant.FAILED_CODE;
    public  String msg=XmjfConstant.FAILED_MSG;

    public NoLoginException() {
        super(XmjfConstant.FAILED_MSG);
    }

    public NoLoginException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public NoLoginException(Integer code) {
        super(XmjfConstant.FAILED_MSG);
        this.code = code;
    }

    public NoLoginException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
