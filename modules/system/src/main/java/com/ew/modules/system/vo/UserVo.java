package com.ew.modules.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="用户VO", description="系统-用户")
public class UserVo {

	@ApiModelProperty(value = "用户标识")
	private Integer userId;
	
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "部门名")
    private String deptName;

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty(value = "头像")
    private String picture;

    @ApiModelProperty(value = "性别（1:男,2:女）")
    private Integer sex;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话号码")
    private String phone;
    
    @ApiModelProperty(value = "备注")
    private String remark;
	
}
