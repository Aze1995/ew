package com.ew.admin.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ew.common.dto.ResultDto;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.modules.system.entity.User;
import com.ew.modules.system.service.IUserService;

import io.swagger.annotations.Api;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/system/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	public ResultDto<List<User>> list(){
		List<User> list = userService.list();
		return ResultDtoUtil.success();
	}
	
}
