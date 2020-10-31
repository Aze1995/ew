package com.ew.modules.system.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 角色菜单权限
 * @author Mr`Huang
 * @Date 2020年10月31日 下午3:18:07
 */
@Data
@TableName(value = "sys_role_menu")
public class RoleMenu implements Serializable {

	private static final long serialVersionUID = 8955455549140226690L;

	@TableId(type = IdType.INPUT,value = "role_id")
	private Long roleId;
	@TableId(type = IdType.INPUT,value = "menu_id")
	private Long menuId;
	
}
