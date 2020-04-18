package com.cp.admin.controller;

import com.cp.admin.param.UserLoginParam;
import com.cp.admin.util.ShiroUtils;
import com.cp.admin.vo.ResultVO;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("validateCode.jpg")
    public void validateCodeImg(HttpServletResponse response, HttpServletRequest request){
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);

        try(ServletOutputStream outputStream = response.getOutputStream()) {
            response.setContentType("image/jpeg");
            response.setHeader("Cache-Control", "no-store, no-cache");
            ImageIO.write(image, "jpg", outputStream);
            ShiroUtils.setKaptchaCode(text);
            logger.info("sessionId,{}", ShiroUtils.getSession().getId());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @PostMapping("loginAction")
    @ResponseBody
    public ResultVO loginAction(@RequestBody UserLoginParam userLoginParam){
        //验证码
        String validateCode = ShiroUtils.getKaptchaCode();
        if(!validateCode.equalsIgnoreCase(userLoginParam.getValidateCode())){
            return ResultVO.error("验证码不正确");
        }
        Subject subject = ShiroUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userLoginParam.getUserName(), userLoginParam.getPassword());
        try {
            subject.login(usernamePasswordToken);
        }catch (UnknownAccountException e){
            return ResultVO.success("账号不存在");
        }catch (IncorrectCredentialsException e) {
            return ResultVO.error("账号或密码不正确");
        }catch (LockedAccountException e) {
            return ResultVO.error("账号已被锁定，请联系管理员");
        }catch (AuthenticationException e) {
            return ResultVO.error("账户验证失败");
        }
        return ResultVO.success();
    }



}
