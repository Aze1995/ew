package com.ew.component.actionLog.action;

import org.springframework.stereotype.Component;

import com.ew.common.utils.HttpServletUtil;
import com.ew.component.actionLog.action.base.ActionSign;
import com.ew.component.actionLog.action.base.ResultLog;
import com.ew.modules.system.entity.ActionLog;

/**
 * 用户日志
 * @author Mr`Huang
 * @Date 2020-11-6 13:49:17
 */
@Component
public class UserActionSign implements ActionSign {

	/**用户登入*/
	public static final String USER_LOGIN = "userLogin";
	/**重置密码*/
	public static final String RESET_PASSWORD = "resetPassWord";
	/**删除用户*/
	public static final String USER_DELETE = "deleteUser";

	/**
	 * 用户登入
	 * @param log
	 */
	public void userLogin(ResultLog log) {
		String username = HttpServletUtil.getParameter("username");
		ActionLog actionLog = log.getActionLog();
		if (!log.isSuccess()) {
			String msg = String.format("后台登录失败：[%s]用户名或密码错误", username);
			actionLog.setMessage(msg);
			actionLog.setOperName(null);
			return;
		}
		actionLog.setMessage("登入成功");
	}
	
	/**
	 * 重置用户登入密码
	 * @param log
	 */
	public void resetPassWord(ResultLog log) {
		Long userId = (Long)log.getJoinPoint().getArgs()[0];
		String msg = String.format("重置[%d]用户登入密码%s", userId,log.isSuccess()?"成功":"失败");
		log.getActionLog().setMessage(msg);
	}
	
	/**
	 * 重置用户登入密码
	 * @param log
	 */
	public void deleteUser(ResultLog log) {
		Long userId = (Long)log.getJoinPoint().getArgs()[0];
		String msg = String.format("删除[%d]用户%s", userId,log.isSuccess()?"成功":"失败");
		log.getActionLog().setMessage(msg);
	}
}
