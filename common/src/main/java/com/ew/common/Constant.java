package com.ew.common;

/**
 * 系统常量
 * @author Mr`Huang
 * @Date 2020年10月31日 下午12:39:39
 */
public class Constant {
	
	public static final String CHARSET_UTF8 = "UTF-8";
	
	/**默认配置常量*/
	public class DefaultConst{
		/** 用户初始化密码 */
		public static final String USER_PASSWORD = "123456";
		/** 菜单，部门 父级 PID */
		public static final long TOP_LEVE_PID = 0L;
		/**系统管理员用户名*/
		public static final String SYSTEM_ADMIN_NAME = "ew";
		/**登入用户*/
		public static final String LOGIN_USER_INFO = "LOGIN_USER_INFO";
		/**登入菜单列表权限*/
		public static final String MENU_PERMISSIONS = "system:menu:list";
	}
	

	/**数据状态常量*/
	public class StatusConst {
	    /** 正常状态码 */
	    public static final int OK = 1;
	    /** 删除状态码 */
	    public static final int DELETE = 2;
	}
	
	/** 缓存名称 */
	public class CacheName{
		/**系统菜单缓存*/
		public static final String MENU = "system-menu";
	}
	
}
