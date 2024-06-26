package com.eatmans.shiro710.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private Boolean rememberMe;
}