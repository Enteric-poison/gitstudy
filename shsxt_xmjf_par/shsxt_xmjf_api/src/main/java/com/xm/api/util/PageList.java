package com.xm.api.util;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @handsome
 * @date 2018/9/8 22:42
 */
public class PageList implements Serializable {
    private static final long serialVersionUID = -8672634803015549499L;

    private Paginator paginator;
    private List list;

    public PageList(List list) {

        if(list instanceof Page){
            paginator=new Paginator((Page)list);
            this.list=list;
        }
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
