package com.ew.common.vo;

import lombok.Data;

/**
 * 登入用户信息
 * @author Mr`Huang
 * @Date 2020-11-7 15:27:36
 */
@Data
public class LoginUser {

	/**用户标识*/
    private Long userId;

    /**用户名*/
    private String username;

    /**用户昵称*/
    private String nickname;
    
    /**用户登入密码*/
    private String password;
}
