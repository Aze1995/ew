package com.ew.component.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;

import com.ew.modules.system.entity.User;

/**
 * @program: ew
 * @author: ZeQun
 * @create: 2020-10-30 16:50
 */
public class ShiroUtil {
	
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static void logout() {
		getSubject().logout();
	}

	public static User getLoginUser() {
		User user = null;
		Object obj = getSubject().getPrincipal();
		if (obj != null) {
			user = new User();
			BeanUtils.copyProperties(user, obj);
		}
		return user;
	}

}