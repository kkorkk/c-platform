package com.cp.admin.util;


import com.cp.admin.constant.SysConstant;
import com.cp.admin.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {

    public static Subject getSubject(){
        Subject subject = SecurityUtils.getSubject();
        return subject;
    }

    public static SysUser getSysUser(){
        Subject subject = getSubject();
        SysUser sysUser = (SysUser)subject.getPrincipal();
        return sysUser;
    }

    public static String getUserName(){
        SysUser sysUser = getSysUser();
        return sysUser.getUserName();
    }

    public static Long getUserId(){
        SysUser sysUser = getSysUser();
        return sysUser.getUserId();
    }

    public static Session getSession(){
        Session session = getSubject().getSession();
        return session;
    }

    public static void setSessionAttribute(Object key, Object val){
        Session session = getSession();
        session.setAttribute(key, val);
    }

    public static Object getSessionAttribute(Object key){
        Session session = getSession();
        Object attribute = session.getAttribute(key);
        return attribute;
    };

    public static void setKaptchaCode(String kaptchaCode){
        setSessionAttribute(SysConstant.SHIRO_KAPTCHA_KEY, kaptchaCode);
    }

    public static String getKaptchaCode(){
        String kaptchaCode = (String)getSessionAttribute(SysConstant.SHIRO_KAPTCHA_KEY);
        return kaptchaCode;
    }

}
