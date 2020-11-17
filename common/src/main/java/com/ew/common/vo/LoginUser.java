package com.ew.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登入用户信息
 * @author Mr`Huang
 * @Date 2020-11-7 15:27:36
 */
@Data
@ApiModel(value="用户信息", description="当前登入用户信息")
public class LoginUser {

	/**用户标识*/
	@ApiModelProperty(value = "用户标识")
    private Long userId;

    /**用户名*/
    @ApiModelProperty(value = "用户名")
    private String username;

    /**用户昵称*/
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    
    /**用户登入密码*/
    @ApiModelProperty(value = "登入用户密码",hidden = true)
    @JsonIgnore//不进行序列化
    private String password;
}
