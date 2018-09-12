package com.xm.api.querys;

import java.io.Serializable;

/**
 * @handsome
 * @date 2018/9/10 19:36
 */
public class BasItemQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = -2925850655010168965L;

    private Integer itemCycle;//项目期限  1:0-30  2:30-90   3:90以上
    private Integer isHistory;// 是否为历史项目 1-历史项目  0-可投项目
    private Integer itemType;// 项目类型
    private Integer itemRate;//排序，1正序，2，倒叙  --年化
    private  Integer cycle;//期限 排序

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getItemRate() {
        return itemRate;
    }

    public void setItemRate(Integer itemRate) {
        this.itemRate = itemRate;
    }

    public Integer getItemCycle() {
        return itemCycle;
    }

    public void setItemCycle(Integer itemCycle) {
        this.itemCycle = itemCycle;
    }

    public Integer getIsHistory() {
        return isHistory;
    }

    public void setIsHistory(Integer isHistory) {
        this.isHistory = isHistory;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }
}
