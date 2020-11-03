package com.ew.modules.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 业务日志
 * 
 * @author Mr`Huang
 * @Date 2020年10月31日 下午1:04:00
 */
@Data
@TableName(value = "sys_action_log")
public class ActionLog implements Serializable {

	private static final long serialVersionUID = -7911491702669735900L;

	@ApiModelProperty(value = "主键ID")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "日志名称")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "日志类型")
	@TableField("type")
	private Integer type;

	@ApiModelProperty(value = "操作IP地址")
	@TableField("ipaddr")
	private String ipaddr;

	@ApiModelProperty(value = "产生日志的类")
	@TableField("clazz")
	private String clazz;

	@ApiModelProperty(value = "产生日志的方法")
	@TableField("method")
	private String method;

	@ApiModelProperty(value = "产生日志的表")
	@TableField("model")
	private String model;

	@ApiModelProperty(value = "日志消息")
	@TableField("message")
	private String message;

	@ApiModelProperty(value = "产生日志的用户昵称")
	@TableField("oper_name")
	private String operName;

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private Date createDate;
	
	// 创建者
	@ApiModelProperty(value = "创建者")
	@TableField(fill = FieldFill.INSERT)
	private Long createBy;

}
