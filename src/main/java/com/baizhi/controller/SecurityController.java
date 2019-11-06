package com.baizhi.controller;

import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RequestMapping("code")
@Controller
public class SecurityController {
    @RequestMapping("getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.获取初始验证码
        String securityCode = SecurityCode.getSecurityCode();
        System.out.println("系统默认的验证码========================" + securityCode);
        //2.将初始验证码存到session中
        request.getSession().setAttribute("securityCode", securityCode);
        //3.生成图片
        BufferedImage image = SecurityImage.createImage(securityCode);
        //4.将生成的图片响应除去；
        response.setContentType("image/png");
        ImageIO.write(image, "png", response.getOutputStream());
    }
}
