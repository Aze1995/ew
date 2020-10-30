package com.ew.modules.system.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @program: ew
 * @author: ZeQun
 * @create: 2020-10-30 16:51
 */
@Data
@TableName(value = "sys_action_log")
public class ActionLog {
	
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String name;
    private Byte type;
    private String ipaddr;
    private String clazz;
    private String method;
    private String model;
    private Long recordId;
    private String message;
    private Date createDate;
    private String operName;
	
}