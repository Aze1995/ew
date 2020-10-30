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

/*
CREATE TABLE `sys_action_log` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID' ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志名称' ,
`type`  tinyint(4) NULL DEFAULT NULL COMMENT '日志类型' ,
`ipaddr`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作IP地址' ,
`clazz`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产生日志的类' ,
`method`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产生日志的方法' ,
`model`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产生日志的表' ,
`record_id`  bigint(20) NULL DEFAULT NULL COMMENT '产生日志的数据id' ,
`message`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '日志消息' ,
`create_date`  datetime NULL DEFAULT NULL COMMENT '记录时间' ,
`oper_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产生日志的用户昵称' ,
`oper_by`  bigint(20) NULL DEFAULT NULL COMMENT '产生日志的用户' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1
ROW_FORMAT=DYNAMIC
;

 * */
