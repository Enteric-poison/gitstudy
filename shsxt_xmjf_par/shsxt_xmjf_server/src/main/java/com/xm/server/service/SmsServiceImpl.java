package com.xm.server.service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.xm.api.constant.XmjfConstant;
import com.xm.api.service.SmsService;
import com.xm.api.util.AssertUtil;
import com.xm.api.util.RandomCodesUtils;
import com.xm.server.utils.PhoneUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @handsome
 * @date 2018/9/9 16:37
 */
//发短信服务
@Service
public class SmsServiceImpl implements SmsService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    //发送短信服务
    @Override
    public void sendSMsg(String mobile, Integer type) {

        //验证手机号
        checkSmsParams(mobile,type);
        //产生随机验证码
        String code= RandomCodesUtils.createRandom(true,4);
        String key=null;//redis 临时存储验证码的主键

        if (type.equals(XmjfConstant.SMS_LOGIN_TYPE)){//手机登录
            sendMsg(mobile,XmjfConstant.SMS_LOGIN_TEMPLATE_CODE,code);
            key=mobile+"::"+XmjfConstant.SMS_LOGIN_TEMPLATE_CODE+"::"+code;
           // redisTemplate.opsForValue().set(key,code,XmjfConstant.SMS_EXPIRE_TIME, TimeUnit.SECONDS);
        } else if (type.equals(XmjfConstant.SMS_REGISTER_TYPE)){//注册端口
            sendMsg(mobile,XmjfConstant.SMS_REGISTER_TEMPLATE_CODE,code);
            key=mobile+"::"+XmjfConstant.SMS_REGISTER_TEMPLATE_CODE+"::"+code;
            //redisTemplate.opsForValue().set(key,code,XmjfConstant.SMS_EXPIRE_TIME, TimeUnit.SECONDS);
        } else if (type.equals(XmjfConstant.SMS_REGISTER_SUCCESS_NOYIRY_TYPE)){
            sendMsg(mobile,XmjfConstant.SMS_REGISTER_TEMPLATE_CODE,code);
            key=mobile+"::"+XmjfConstant.SMS_REGISTER_TEMPLATE_CODE+"::"+code;
        }else {
            System.err.println("短信类型错误");
        }
        redisTemplate.opsForValue().set(key,code,XmjfConstant.SMS_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    //发送短信端口
    private void sendMsg(String mobile, String smsLoginTemplateCode, String code) {
        try {
            System.setProperty("sun.net.client.defaultConnectTimeout", "20000");
            System.setProperty("sun.net.client.defaultReadTimeout", "20000");
            final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
            final String accessKeyId = "***";//你的accessKeyId,参考本文档步骤2
            final String accessKeySecret = "****";//你的accessKeySecret，参考本文档步骤2
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            request.setPhoneNumbers(mobile);
            request.setSignName("****");
            request.setTemplateCode(smsLoginTemplateCode);
            Map<String,String> map=new HashMap<String,String>();
            map.put("code",code);
            request.setTemplateParam(JSON.toJSONString(map));
            request.setOutId("yourOutId");
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            System.out.println(sendSmsResponse.getCode()+"--"+sendSmsResponse.getMessage()+"--"+sendSmsResponse.getBizId()+"--"+sendSmsResponse.getRequestId());
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                System.out.println("短信发送成功!!!");
            }
        }catch (ServerException e) {
            e.printStackTrace();
        }  catch (ClientException e) {
            e.printStackTrace();
        }
    }

    //手机号和类型验证
    private void checkSmsParams(String mobile, Integer type) {
        PhoneUtil.checkPhone(mobile);
        AssertUtil.isTrue(type==null||!(type.equals(XmjfConstant.SMS_LOGIN_TYPE)||type.equals(XmjfConstant.SMS_REGISTER_TYPE)||type.equals(XmjfConstant.SMS_REGISTER_SUCCESS_NOYIRY_TYPE)),"短信类型不合法");
    }
}
