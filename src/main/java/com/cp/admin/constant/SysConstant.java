package com.cp.admin.constant;

public class SysConstant {

    public static final Integer RESULT_CODE_SUCCESS_DEFAULT = 0;
    public static final String RESULT_MESSAGE_SUCCESS_DEFAULT = "处理成功";

    public static final Integer RESULT_CODE_ERROR_DEFAULT = -1;
    public static final String RESULT_MESSAGE_ERROR_DEFAULT = "系统错误";

    /*删除标记-已删除*/
    public static final Byte DELETE_FLAG_TRUE = -1;
    /*删除标记-未删除*/
    public static final Byte DELETE_FLAG_FALSE = 0;

    /*shiro密码加密算法*/
    public static final String SHIRO_HASH_ALGORITHM_NAME = "MD5";
    /*shiro密码加密循环次数*/
    public static final Integer SHIRO_HASH_ITERATIONS = 1;
    /*shiro-kaptcha-key*/
    public static final String SHIRO_KAPTCHA_KEY = "shiro_kaptcha_code";


}
