package com.xm.server.utils;

import com.xm.api.util.AssertUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @handsome
 * @date 2018/9/6 22:37
 */
//手机号验证

public class PhoneUtil {

    public static void checkPhone(String mobile){
        AssertUtil.isTrue(StringUtils.isBlank(mobile)||mobile.length()!=11,"手机号不合法");
        String check = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$";
        Pattern regex=Pattern.compile(check);
        Matcher matcher=regex.matcher(mobile);
        AssertUtil.isTrue(!matcher.matches(),"没有该手机号");
    }
}
