package com.xm.server.service;

import com.github.pagehelper.PageHelper;
import com.xm.api.constant.ItemStatus;
import com.xm.api.querys.BasItemQuery;
import com.xm.api.service.BasItemService;
import com.xm.api.util.PageList;
import com.xm.server.db.dao.BasItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
            }
        }
        return new PageList(vals);
    }
}
