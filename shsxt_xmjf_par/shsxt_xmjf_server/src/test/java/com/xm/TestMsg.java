package com.xm;

import com.xm.api.constant.XmjfConstant;
import com.xm.api.service.SmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @handsome
 * @date 2018/9/9 17:10
 */

//短信测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TestMsg {
    @Resource
    private SmsService smsService;

    @Test
    public void test01(){
        smsService.sendSMsg("15718871127", XmjfConstant.SMS_REGISTER_TYPE);
    }
}
