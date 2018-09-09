package com.xm.web.controller;

import com.xm.api.constant.XmjfConstant;
import com.xm.api.exceptions.BusiException;
import com.xm.api.model.ResultInfo;
import com.xm.api.service.SmsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @handsome
 * @date 2018/9/9 17:23
 */
@Controller
public class SmsController {
    @Resource
    private SmsService smsService;

    @ResponseBody
    @RequestMapping("sendMsg")
    public ResultInfo sendSmsToPhone(String mobile, String imageCode,Integer type, HttpSession session){
        ResultInfo resultInfo=new ResultInfo();
        /**
         * 校验验证码是否正确
         */
        if(StringUtils.isBlank(imageCode)){
            resultInfo.setCode(XmjfConstant.FAILED_CODE);
            resultInfo.setMsg("验证码不能为空!");
            return resultInfo;
        }

        String sessionImageCode= (String) session.getAttribute(XmjfConstant.IMAGE_VERIFY);
        if(StringUtils.isBlank(sessionImageCode)){
            resultInfo.setCode(XmjfConstant.FAILED_CODE);
            resultInfo.setMsg("验证码已失效,请刷新页面!");
            return resultInfo;
        }

        if(!(imageCode.equals(sessionImageCode))){
            resultInfo.setCode(XmjfConstant.FAILED_CODE);
            resultInfo.setMsg("验证码输入错误!");
            return resultInfo;
        }

        // 移除验证码
        session.removeAttribute(XmjfConstant.IMAGE_VERIFY);

        try {
            smsService.sendSMsg(mobile, type);
        }catch (BusiException e) {
                e.printStackTrace();
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(XmjfConstant.FAILED_CODE);
            resultInfo.setMsg(XmjfConstant.FAILED_MSG);
        }


        return resultInfo;
    }
}
