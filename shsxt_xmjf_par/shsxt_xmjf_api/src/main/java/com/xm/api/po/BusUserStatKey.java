package com.xm.api.po;

import java.io.Serializable;

public class BusUserStatKey implements Serializable {
    private static final long serialVersionUID = -5033204188390755216L;
    private Integer id;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}