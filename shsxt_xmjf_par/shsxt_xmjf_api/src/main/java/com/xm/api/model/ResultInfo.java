package com.xm.api.model;

import com.xm.api.constant.XmjfConstant;

import java.io.Serializable;

/**
 * @handsome
 * @date 2018/9/9 15:08
 */
public class ResultInfo implements Serializable {
    private static final long serialVersionUID = 4269850251152786850L;

    private Integer code= XmjfConstant.SUCCESS_CODE;
    private String msg=XmjfConstant.SUCCESS_MSG;

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
