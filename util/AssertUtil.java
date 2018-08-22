package com.shsxt.crm.util;

import com.shsxt.crm.exception.LoginException;
import com.shsxt.crm.exception.ParamException;



/**
 * @handsome
 * @date 2018/8/20 19:51
 */
public class AssertUtil {

    public static void isTrue(Boolean flag,String msg){
        if (flag){
            throw new ParamException(msg);
        }
    }

    public static void isTrue(Boolean flag,String msg,Integer code){
        if (flag){
            throw new ParamException(code,msg);
        }
    }

    public static void isNotLogin(Boolean flag,String msg) {
        if (flag){
            throw new LoginException(msg);
        }
    }
}
