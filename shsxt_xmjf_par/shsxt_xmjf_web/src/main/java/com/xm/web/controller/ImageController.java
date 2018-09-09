package com.xm.web.controller;

import com.google.code.kaptcha.Producer;
import com.xm.api.constant.XmjfConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @handsome
 * @date 2018/9/9 16:14
 */
    //动态图片页面
@Controller
public class ImageController {

    @Resource
    private Producer producer;

    @RequestMapping("image")
    public void getImage(HttpServletResponse response, HttpSession session) throws IOException {
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");

        //创建验证码文本
        String code= producer.createText();
        System.out.println(code);

        //存session
        session.setAttribute(XmjfConstant.IMAGE_VERIFY,code);
        //创建图片验证
           BufferedImage image= producer.createImage(code);
        //流输出到前台页面
        ServletOutputStream servletOutputStream=response.getOutputStream();
        ImageIO.write(image,"jpg",servletOutputStream);
    }
}

