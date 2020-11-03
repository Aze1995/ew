package com.ew.admin.system.form;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色
 * @author Mr`Huang
 * @Date 2020-11-3 15:16:19
 */
@Data
@ApiModel(value="角色Form", description="角色表单")
public class RoleFrom {
	
	@NotBlank
    @ApiModelProperty(value = "角色名称（中文名）")
    private String title;
}
