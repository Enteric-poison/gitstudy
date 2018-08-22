package com.shsxt.crm.base;

/**
 * @handsome
 * @date 2018/8/21 21:44
 */
//分页
public class BaseQuery {
    private Integer pageNum=1;
    private Integer pageSize=10;

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
