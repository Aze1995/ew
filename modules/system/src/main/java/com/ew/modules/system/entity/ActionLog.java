package com.ew.modules.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 业务日志
 * @author Mr`Huang
 * @Date 2020年10月31日 下午1:04:00
 */
@Data
@TableName(value = "sys_action_log")
public class ActionLog implements Serializable{
	
	private static final long serialVersionUID = -7911491702669735900L;

	@TableId(type = IdType.AUTO)
    private Long id;
    
    private String name;
    private Byte type;
    private String ipaddr;
    private String clazz;
    private String method;
    private String model; 
    
    /**日志消息*/
    private String message;
    /**产生日志的用户昵称*/
    private String operName;
    
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;
    // 创建者
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
	
}
 
