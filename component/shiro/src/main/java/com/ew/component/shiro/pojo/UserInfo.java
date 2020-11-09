package com.ew.component.shiro.pojo;

import java.io.Serializable;
import java.util.List;

import com.ew.modules.system.entity.User;
import com.ew.modules.system.vo.MenuVo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登入用户信息
 * 
 * @author Mr`Huang
 * @Date 2020-11-7 10:37:04
 */
@Data
@NoArgsConstructor
public class UserInfo implements Serializable{

	private static final long serialVersionUID = 885226850082629248L;

	public UserInfo(User user) {
		super();
		this.user = user;
	}
	
	/** 登入用户信息 */
	User user;

	/** 用户菜单列表 */
	List<MenuVo> menus;
}
