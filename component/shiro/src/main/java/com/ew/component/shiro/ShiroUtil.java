package com.ew.component.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;

import com.ew.common.Constant.DefaultConst;
import com.ew.modules.system.entity.User;
import com.ew.modules.system.vo.MenuVo;

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

	/**
	 * 登入用户信息
	 * @return
	 */
	public static User getLoginUser() {
		User user = null;
		Object obj = getSubject().getPrincipal();
		if (obj != null) {
			user = new User();
			BeanUtils.copyProperties(((UserInfo)obj).getUser(), user);
		}
		return user;
	}
	
	/**
	 * 获取用户菜单
	 * @return
	 */
	public static List<MenuVo> getUserMenu() {
		Object obj = getSubject().getPrincipal();
		if (obj != null) {
			return ((UserInfo)obj).getMenus();
		}
		return null;
	}
	
	
	public static boolean isAdmin() {
		User user = getLoginUser();
		if (user != null) {
			return DefaultConst.SYSTEM_ADMIN_NAME.equals(user.getUsername());
		}
		return false;
	}

}