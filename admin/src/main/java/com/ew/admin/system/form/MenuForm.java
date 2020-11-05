package com.ew.admin.system.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="菜单Form", description="菜单表单")
public class MenuForm {

	@NotBlank
	@ApiModelProperty(notes = "菜单名称")
	private String title;
	
	@NotNull
	@Min(value = 1)
	@ApiModelProperty(notes = "父级编号")
	private Long pid;
	
	@NotBlank
	@ApiModelProperty(notes = "URL地址")
	private String url;
	
	@NotBlank
	@ApiModelProperty(notes = "权限标识")
	private String perms;
	
	@NotBlank
	@ApiModelProperty(notes = "图标")
	private String icon;
	
	@NotNull
	@Min(value = 1)
	@Max(value = 3)
	@ApiModelProperty(notes = "类型（1:一级菜单,2:子级菜单,3:不/**是菜单")
	private Integer type;
	
	@Min(value = 1)
	@ApiModelProperty(value = "排序")
	private Integer sort;
	
}
