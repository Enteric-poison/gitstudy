package com.xm;

import com.xm.api.po.BasUser;
import com.xm.api.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @handsome
 * @date 2018/9/9 15:44
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class test {
    @Resource
    private UserService userService;

    @Test
    public  void test01(){
        BasUser user= userService.queryUserByName("hcjf20165");
        System.out.println(user.getId()+":userName:"+user.getUsername());
    }
    @Test
    public void test02(){
        BasUser user=userService.queryByPhone("18321530938");
        System.out.println(user.getId());
    }
}
