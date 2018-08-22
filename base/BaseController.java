package com.shsxt.crm.base;

import com.shsxt.crm.model.ResultInfo;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @handsome
 * @date 2018/8/20 11:04
 */
public class BaseController {

    @ModelAttribute
    public void preMethod(HttpServletRequest request){
       request.setAttribute("ctx",request.getContextPath());
    }

    public ResultInfo success(Integer code,String msg,Object result){
        ResultInfo resultInfo=new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }
    public ResultInfo success(String msg,Object result){
        ResultInfo resultInfo=new ResultInfo();

        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }
    public ResultInfo success(Object result){
        ResultInfo resultInfo=new ResultInfo();


        resultInfo.setResult(result);
        return resultInfo;
    }

    public ResultInfo success(String msg){
        ResultInfo resultInfo=new ResultInfo();

        resultInfo.setMsg(msg);

        return resultInfo;
    }


}
