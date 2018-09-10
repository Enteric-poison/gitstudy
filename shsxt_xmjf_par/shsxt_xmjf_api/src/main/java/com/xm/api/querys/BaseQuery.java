package com.xm.api.querys;

import java.io.Serializable;

/**
 * @handsome
 * @date 2018/9/10 19:41
 */
public class BaseQuery implements Serializable {
    private static final long serialVersionUID = 5692791820621130433L;

    private Integer pageNum=1;//当前页页码
    private Integer pageSize=10;//每页大小

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
