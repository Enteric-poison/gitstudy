package com.xm.web.controller;

import com.xm.api.model.ResultInfo;
import com.xm.api.po.BasItem;
import com.xm.api.po.BasUserSecurity;
import com.xm.api.querys.BasItemQuery;
import com.xm.api.service.BasItemService;
import com.xm.api.service.BasUserSecurityService;
import com.xm.api.util.PageList;
import com.xm.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @handsome
 * @date 2018/9/9 22:52
 */
@Controller
@RequestMapping("item")
public class BasItemController extends BaseController {

    @Resource
    private BasItemService basItemService;

    @Resource
    private BasUserSecurityService basUserSecurityService;


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

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateStatus(Integer itemId){
        basItemService.updateStatus(itemId);
        return new ResultInfo();
    }


    @RequestMapping("details/{itemId}")
    public String details(@PathVariable Integer itemId, HttpServletRequest request){
        BasItem basItem= basItemService.queryBasItemByItemId(itemId);
        request.setAttribute("item",basItem);


        //获取借款人信息
        Integer loanUserId=  basItem.getItemUserId();
        BasUserSecurity loanUser=basUserSecurityService.queryBasUserSecurityByUserId(loanUserId);
        request.setAttribute("loanUser",loanUser);
        return "details";
    }




}
