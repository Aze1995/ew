package com.ew.admin.system.controller;

import javax.validation.constraints.NotBlank;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ew.common.dto.ResultDto;
import com.ew.common.enums.ActionLogEnum;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.component.actionLog.action.UserActionSign;
import com.ew.component.actionLog.annotation.ActionLog;
import com.ew.modules.system.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "系统登入")
@Validated
@RestController
@RequestMapping("/system")
public class LoginController {
	
	@ApiOperation(value = "登入")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username",value = "用户名",required = false,paramType = "query"),
		@ApiImplicitParam(name = "password",value = "用户密码",required = false,paramType = "query"),
	})
	@ActionLog(name = "登入",type = ActionLogEnum.LOGIN,key = UserActionSign.USER_LOGIN,action = UserActionSign.class)
	@PostMapping("login")
	public ResultDto<User> login( @NotBlank @RequestParam(name = "username") String username,
			@NotBlank @RequestParam(name = "password") String password){
        // 1.获取Subject主体对象
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 3.执行登录，进入自定义Realm类中
        try {
            subject.login(token);
            return ResultDtoUtil.success();
        } catch (AuthenticationException e) {
            return ResultDtoUtil.RequestError.business("用户名或密码错误");
        }
	}
	
	@ApiOperation(value = "退出登入")
	@RequiresAuthentication
	@PostMapping("logout")
	public ResultDto<User> logout(){
		// 1.获取Subject主体对象
        Subject subject = SecurityUtils.getSubject();
        // 2退出登入
        subject.logout();
        return ResultDtoUtil.success();
	}
	
}
