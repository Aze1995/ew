package com.ew.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ew.common.base.BaseEntity;

import lombok.Data;

/**
 * 角色
 * @author Mr`Huang
 * @Date 2020年10月31日 下午1:03:41
 */
@Data
@TableName(value = "sys_role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 2785919255739562019L;

	@TableId(type = IdType.AUTO,value = "role_id")
	private Long roleId;
	/**角色名称（中文名）*/
	private String title;
	
}
