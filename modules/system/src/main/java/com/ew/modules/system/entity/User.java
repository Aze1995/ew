package com.ew.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ew.common.base.BaseEntity;

import lombok.Data;

/**
 * 用户
 * @author Mr`Huang
 * @Date 2020年10月31日 下午3:18:25
 */
@Data
@TableName(value = "sys_user")
public class User extends BaseEntity{

	private static final long serialVersionUID = -903471106317669182L;
	
	/**主键ID*/
	@TableId(type = IdType.AUTO,value = "user_id")
	private Integer userId;
	/**用户名*/
	private String username;
	/**用户昵称*/
	private String nickname;
	/**密码*/
	private String password;
	/**部门ID*/
	private Integer deptId;
	/**角色id*/
	private Integer roleId;
	/**头像*/
	private String picture;
	/**性别（1:男,2:女）*/
	private Byte sex;
	/**邮箱*/
	private String email;
	/**电话号码*/
	private String phone;

}
