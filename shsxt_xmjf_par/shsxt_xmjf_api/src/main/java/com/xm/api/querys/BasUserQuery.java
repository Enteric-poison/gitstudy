package com.xm.api.querys;

import java.io.Serializable;

/**
 * @handsome
 * @date 2018/9/9 17:50
 */
public class BasUserQuery implements Serializable {
    private static final long serialVersionUID = 2969020846496922210L;

    private Integer id;
    private String username;
    private String mobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
