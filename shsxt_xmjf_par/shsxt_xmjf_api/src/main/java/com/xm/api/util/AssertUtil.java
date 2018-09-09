package com.xm.api.util;

import com.xm.api.exceptions.BusiException;

public class AssertUtil {
    public static void isTrue(Boolean flag,String msg) {
        if(flag){
            throw new BusiException(msg);
        }
    }
}
