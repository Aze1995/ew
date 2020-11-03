package com.ew.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ew.common.base.BaseEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单
 * @author Mr`Huang
 * @Date 2020年10月31日 下午3:00:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_menu")
public class Menu extends BaseEntity {
	
	private static final long serialVersionUID = -1426837237018984798L;
	
	   @ApiModelProperty(value = "主键ID")
	    @TableId(value = "menu_id", type = IdType.AUTO)
	    private Long menuId;

	    @ApiModelProperty(value = "菜单名称")
	    @TableField("title")
	    private String title;

	    @ApiModelProperty(value = "父级编号")
	    @TableField("pid")
	    private Long pid;

	    @ApiModelProperty(value = "所有父级编号")
	    @TableField("pids")
	    private String pids;

	    @ApiModelProperty(value = "URL地址")
	    @TableField("url")
	    private String url;

	    @ApiModelProperty(value = "权限标识")
	    @TableField("perms")
	    private String perms;

	    @ApiModelProperty(value = "图标")
	    @TableField("icon")
	    private String icon;

	    @ApiModelProperty(value = "类型（1:一级菜单,2:子级菜单,3:不是菜单）")
	    @TableField("type")
	    private Integer type;

	    @ApiModelProperty(value = "排序")
	    @TableField("sort")
	    private Integer sort;
}
