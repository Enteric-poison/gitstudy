package com.xm.api.model;

import java.io.Serializable;

/**
 * @handsome
 * @date 2018/9/9 21:03
 */
public class UserModel implements Serializable {
    private static final long serialVersionUID = -2944239038145458256L;

    private Integer id;
    private String username;
    private String password;
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
