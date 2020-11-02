package com.ew.modules.system.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("菜单")
public class MenuVo implements Serializable {

	private static final long serialVersionUID = -6692788354167638339L;
	
	@ApiModelProperty(notes = "菜单标识")
	private Long menuId;
	
	@ApiModelProperty(notes = "菜单名称")
	private String title;
	
	@ApiModelProperty(notes = "父级编号")
	private Long pid;
	
	@ApiModelProperty(notes = "URL地址")
	private String url;
	
	@ApiModelProperty(notes = "权限标识")
	private String perms;
	
	@ApiModelProperty(notes = "图标")
	private String icon;
	
	@ApiModelProperty(notes = "类型（1:一级菜单,2:子级菜单,3:不/**是菜单")
	private String type;
	
	@ApiModelProperty(notes = "子级菜单")
	private List<MenuVo> menu;
	
}
