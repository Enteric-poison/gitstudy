package com.xm.server.service;

import com.github.pagehelper.PageHelper;
import com.xm.api.constant.ItemStatus;
import com.xm.api.constant.XmjfConstant;
import com.xm.api.po.BasItem;
import com.xm.api.querys.BasItemQuery;
import com.xm.api.service.BasItemService;
import com.xm.api.util.AssertUtil;
import com.xm.api.util.PageList;
import com.xm.server.db.dao.BasItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @handsome
 * @date 2018/9/10 20:17
 */
@Service
public class BasItemServiceImpl implements BasItemService {

    @Resource
    private BasItemMapper basItemMapper;

    @Override
    public PageList queryBasItemsByParams(BasItemQuery basItemQuery) {
        /**
         * pageHelper 开启分页查询环境
         */
        PageHelper.startPage(basItemQuery.getPageNum(),basItemQuery.getPageSize());
        // 执行dao 列表查询
        List<Map<String, Object>> vals= basItemMapper.queryByParams(basItemQuery);
        System.out.println(vals);
        if (!CollectionUtils.isEmpty(vals)){
            for (Map<String,Object> map:vals){
                int itemStatus= (int) map.get("item_status");
                  if (itemStatus== ItemStatus.OPEN){
                      BigDecimal itemAccount= (BigDecimal) map.get("item_account");
                      System.out.println(itemAccount);
                      BigDecimal itemOnGoingAccount= (BigDecimal) map.get("item_ongoing_account");

                      map.put("syAmount",itemAccount.subtract(itemOnGoingAccount));

                  }

                  //等待开放
                if (itemStatus==ItemStatus.WAITOPEN){
                    Date relaseTime= (Date) map.get("release_time");
                    Long syTime=(relaseTime.getTime()-new Date().getTime())/1000;
                    System.out.println(new Date().getTime());
                    map.put("syTime",syTime);
                }


            }
        }
        return new PageList(vals);
    }

    //修改状态值
    @Override
    public void updateStatus(Integer itemId) {
        BasItem basItem =basItemMapper.queryById(itemId);
        basItem.setItemStatus(ItemStatus.OPEN);
        basItem.setUpdateTime(new Date());
        AssertUtil.isTrue(basItemMapper.update(basItem)<1, XmjfConstant.FAILED_MSG);
    }

    @Override
    public BasItem queryBasItemByItemId(Integer itemId) {
        return basItemMapper.queryById(itemId);


    }
}
