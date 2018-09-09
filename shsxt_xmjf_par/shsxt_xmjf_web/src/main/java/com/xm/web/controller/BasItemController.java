package com.xm.web.controller;

import com.xm.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @handsome
 * @date 2018/9/9 22:52
 */
@Controller
@RequestMapping("item")
public class BasItemController extends BaseController {

    @RequestMapping("index")
    public String index(){
        return "invest_list";
    }
}
