package com.ew.common.utils;

import com.ew.common.Constant.DefaultConst;
import com.ew.common.vo.LoginUser;

/**
 * 登入用户工具
 * @author Mr`Huang
 * @Date 2020-11-7 15:26:15
 */
public class LoginUserUtil {

	/**
	 * 设置登入用户信息
	 * @param user
	 */
	public static void setLoginUser(LoginUser user) {
		HttpServletUtil.getRequest().setAttribute(DefaultConst.LOGIN_USER_INFO, user);
	}
	
	/**
	 * 获取登入用户信息
	 * @return
	 */
	public static LoginUser getLoginUser() {
		Object userInfo = HttpServletUtil.getRequest().getAttribute(DefaultConst.LOGIN_USER_INFO);
		if (userInfo == null) {
			return null;
		}
		return (LoginUser)userInfo;
	}
	
	public static boolean isLogin() {
		return getLoginUser() == null;
	}
	
	/**
	 * 获取登入用户标识
	 * @return
	 */
	public static Long getLoginUserId() {
		LoginUser user = getLoginUser();
		return user == null?null:user.getUserId();
	}
	
	/**
	 * 获取登入用户昵称
	 * @return
	 */
	public static String getLoginNickName() {
		LoginUser user = getLoginUser();
		return user == null?null:user.getUsername();
	}
	
	
}
