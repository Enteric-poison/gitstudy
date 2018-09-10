package com.xm.web.controller;

import com.xm.api.querys.BasItemQuery;
import com.xm.api.service.BasItemService;
import com.xm.api.util.PageList;
import com.xm.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @handsome
 * @date 2018/9/9 22:52
 */
@Controller
@RequestMapping("item")
public class BasItemController extends BaseController {

    @Resource
    private BasItemService basItemService;

    @RequestMapping("index")
    public String index(){
        return "invest_list";
    }


    @RequestMapping("list")
    @ResponseBody
    public PageList queryBasItemsByParams(BasItemQuery basItemQuery){
        PageList pageList= basItemService.queryBasItemsByParams(basItemQuery);
        System.out.println(pageList.getPaginator());
        return pageList;
    }

}
