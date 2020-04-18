package com.cp.admin.param;

import lombok.Data;

@Data
public class UserLoginParam {

    private String userName;
    private String password;
    private String validateCode;

}
