package com.ew.admin.system.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="用户Form", description="用户表单")
public class UserForm {
	
	@NotBlank
    @ApiModelProperty(value = "用户名")
    private String username;

	@NotBlank
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

	@NotBlank
    @ApiModelProperty(value = "密码")
    private String password;
	
	@NotBlank
    @ApiModelProperty(value = "电话号码")
    private String phone;

	@NotNull
	@Min(value = 1)
    @ApiModelProperty(value = "部门ID")
    private Integer deptId;

	@NotNull
	@Min(value = 1)
    @ApiModelProperty(value = "角色id")
    private Integer roleId;
	
    @ApiModelProperty(value = "头像")
    private String picture;

    @NotNull
	@Min(value = 1)
    @Max(value = 2)
    @ApiModelProperty(value = "性别（1:男,2:女）")
    private Integer sex;

    @ApiModelProperty(value = "邮箱")
    private String email;


}
