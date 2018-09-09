package com.xm.server.service;

import com.xm.api.constant.XmjfConstant;
import com.xm.api.model.UserModel;
import com.xm.api.po.BasExperiencedGold;
import com.xm.api.po.BasUser;
import com.xm.api.service.SmsService;
import com.xm.api.service.UserService;
import com.xm.api.util.AssertUtil;
import com.xm.api.util.MD5;
import com.xm.api.util.RandomCodesUtils;
import com.xm.server.db.dao.BasExperiencedGoldMapper;
import com.xm.server.db.dao.BasUserMapper;
import com.xm.server.utils.DateUtils;
import com.xm.server.utils.PhoneUtil;
import org.apache.commons.lang3.StringUtils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @handsome
 * @date 2018/9/9 15:34
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private BasUserMapper basUserMapper;
    @Resource
    private BasExperiencedGoldMapper basExperiencedGoldMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private SmsService smsService;

    @Override
    public BasUser queryUserByName(String userName) {
        return basUserMapper.queryUserByName(userName);
    }

    @Override
    public BasUser queryByPhone(String mobile) {
        return basUserMapper.queryByPhone(mobile);
    }

    @Override
    public void saveUser(String mobile, String password, String code) {
        //验证参数
        checkUserParams(mobile,code,password);
        // 主表信息初始化
        Integer userId= initBasUser(mobile,password);
        // 初始化用户信息扩展数据
        initBasUserInfo(mobile,userId);
        // 发送通知短信
        smsService.sendSMsg(mobile,XmjfConstant.SMS_REGISTER_SUCCESS_NOYIRY_TYPE);

    }

    //普通用户登录
    @Override
    public UserModel userLogin(String mobile, String password) {
        //验证参数
        checkParams(mobile,password);
        BasUser basUser= basUserMapper.queryByPhone(mobile);
        AssertUtil.isTrue(basUser==null,"用户不存在");
        String newp= MD5.GetMD5Code(password+basUser.getSalt());//获取盐
        AssertUtil.isTrue(!(basUser.getPassword().equals(newp)),"密码错误");
        return buildUserInfo(basUser);
    }

    //快速登录
    @Override
    public UserModel quickLogin(String mobile, String code) {
        checkQuickLoginParams(mobile,code);
        BasUser basUser=basUserMapper.queryByPhone(mobile);
        AssertUtil.isTrue(null ==basUser,"该手机号暂未注册!");
        return buildUserInfo(basUser);
    }

    //快速登录校验
    private void checkQuickLoginParams(String mobile, String code) {
        //验证手机号
        PhoneUtil.checkPhone(mobile);
        String  key=mobile+"::"+ XmjfConstant.SMS_LOGIN_TEMPLATE_CODE+"::"+code;
        System.out.println("key:"+key);
        AssertUtil.isTrue(!redisTemplate.hasKey(key),"验证码不存在或者已失效");
        String cacheCode= (String) redisTemplate.opsForValue().get(key);
        AssertUtil.isTrue(!(cacheCode.equals(code)),"验证码输出错误!");
    }

    //封装返回信息
    private UserModel buildUserInfo(BasUser basUser) {
        UserModel userModel=new UserModel();
        userModel.setId(basUser.getId());
        userModel.setUsername(basUser.getUsername());
        userModel.setMobile(basUser.getMobile());
        return userModel;
    }

    //验证手机是否为空，密码
    private void checkParams(String mobile, String password) {
        PhoneUtil.checkPhone(mobile);
        AssertUtil.isTrue(StringUtils.isBlank(password),"密码不能为空");
    }

    // 初始化用户信息扩展数据
    private void initBasUserInfo(String mobile, Integer userId) {
        BasExperiencedGold basExperiencedGold=new BasExperiencedGold();
        basExperiencedGold.setWay("用户注册");
        basExperiencedGold.setUserId(userId);
        basExperiencedGold.setUsefulLife(15);// 15 天有效
        basExperiencedGold.setStatus(2);// 未使用
        basExperiencedGold.setGoldName("用户注册体验金");
        basExperiencedGold.setExpiredTime(DateUtils.addDays(new Date(),15));
        basExperiencedGold.setAmount(BigDecimal.valueOf(2888));
        basExperiencedGold.setAddtime(new Date());
        AssertUtil.isTrue(basExperiencedGoldMapper.save(basExperiencedGold)<1,XmjfConstant.FAILED_MSG);
    }

    //注册用户表初始化
    private Integer initBasUser(String mobile, String password) {
        BasUser basUser=new BasUser();
        basUser.setUsername(mobile);
        basUser.setMobile(mobile);
        basUser.setReferer("PC");
        basUser.setStatus(1);
        basUser.setTime(new Date());
        basUser.setAddtime(new Date());
        String salt=RandomCodesUtils.createRandom(false,6);
        basUser.setSalt(salt);
        basUser.setPassword(MD5.GetMD5Code(password+salt));
        basUser.setType(1);// 投资用户
        AssertUtil.isTrue(basUserMapper.save(basUser)<1,XmjfConstant.FAILED_MSG);
        return basUser.getId();
    }

    //验证参数
    private void checkUserParams(String mobile, String code, String password) {
        PhoneUtil.checkPhone(mobile);
        //查询手机号是否已被注册
        AssertUtil.isTrue(basUserMapper.queryByPhone(mobile)!=null,"该手机号已被注册");
        AssertUtil.isTrue(StringUtils.isBlank(code),"短信验证码不能为空");
        String  key=mobile+"::"+ XmjfConstant.SMS_REGISTER_TEMPLATE_CODE+"::"+code;
        AssertUtil.isTrue(!redisTemplate.hasKey(key),"短信验证码错误或已失效");
        String cacheCode= (String) redisTemplate.opsForValue().get(key);
        AssertUtil.isTrue(!code.equals(cacheCode),"短信验证码错误");
        AssertUtil.isTrue(StringUtils.isBlank(password),"密码不能为空");
        AssertUtil.isTrue(password.length()<6,"密码至少六位");

    }


}
