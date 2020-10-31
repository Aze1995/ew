package com.ew.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ew.common.base.BaseEntity;

import lombok.Data;

/**
 * 菜单
 * @author Mr`Huang
 * @Date 2020年10月31日 下午3:00:20
 */
@Data
@TableName(value = "sys_menu")
public class Menu extends BaseEntity {
	
	private static final long serialVersionUID = -1426837237018984798L;
	
	/**主键ID*/
	@TableId(value = "menu_id",type = IdType.AUTO)
	private Long menuId;
	/**菜单名称*/
	private String title;
	/**父级编号*/
	private Long pid;
	/**所有父级编号*/
	private String pids;
	/**URL地址*/
	private String url;
	/**权限标识*/
	private String perms;
	/**图标*/
	private String icon;
	/**类型（1:一级菜单,2:子级菜单,3:不/**是菜单）*/
	private String type;
	/**排序*/
	private Integer sort;
}
