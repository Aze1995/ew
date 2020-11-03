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
 * 角色
 * @author Mr`Huang
 * @Date 2020年10月31日 下午1:03:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 2785919255739562019L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    @ApiModelProperty(value = "角色名称（中文名）")
    @TableField("title")
    private String title;
	
}
