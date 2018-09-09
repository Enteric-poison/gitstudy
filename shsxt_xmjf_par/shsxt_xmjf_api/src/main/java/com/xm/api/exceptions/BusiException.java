package com.xm.api.exceptions;

import com.xm.api.constant.XmjfConstant;

/**
 * @handsome
 * @date 2018/9/9 15:05
 */
public class BusiException extends RuntimeException {

    private  Integer code= XmjfConstant.FAILED_CODE;
    public  String msg=XmjfConstant.FAILED_MSG;


    public BusiException() {
        super(XmjfConstant.FAILED_MSG);
    }

    public BusiException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BusiException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusiException(Integer code) {
        super(XmjfConstant.FAILED_MSG);
        this.code = code;
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
