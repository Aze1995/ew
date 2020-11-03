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
 * 部门
 * @author Mr`Huang
 * @Date 2020年10月31日 下午2:54:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_dept")
public class Dept extends BaseEntity {

	private static final long serialVersionUID = 7025213309825596996L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    @ApiModelProperty(value = "部门名称")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "父级ID")
    @TableField("pid")
    private Long pid;

    @ApiModelProperty(value = "所有父级编号")
    @TableField("pids")
    private String pids;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;
	
}
