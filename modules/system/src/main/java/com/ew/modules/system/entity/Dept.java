package com.ew.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ew.common.base.BaseEntity;

import lombok.Data;

/**
 * 部门
 * @author Mr`Huang
 * @Date 2020年10月31日 下午2:54:23
 */
@Data
@TableName(value = "sys_dept")
public class Dept extends BaseEntity {

	private static final long serialVersionUID = 7025213309825596996L;

	@TableId(value = "dept_id",type = IdType.AUTO)
	private Long deptId;
	
	/**部门名称*/
	private String title;
	
	/**父级ID*/
	private Long pid;
	
	/**所有父级编号*/
	private String pids;
	
	/**排序*/
	private Integer sort;
	
}
