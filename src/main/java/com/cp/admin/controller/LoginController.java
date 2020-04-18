package com.cp.admin.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("validateCode.jpg")
    public void validateCodeImg(HttpServletResponse response){
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);

        try(ServletOutputStream outputStream = response.getOutputStream()) {
            response.setContentType("image/jpeg");
            response.setHeader("Cache-Control", "no-store, no-cache");
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
