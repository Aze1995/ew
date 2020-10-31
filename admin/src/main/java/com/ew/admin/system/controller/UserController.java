package com.ew.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ew.common.dto.ResultDto;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.common.utils.ResultDtoUtil.RequestError;
import com.ew.modules.system.entity.User;
import com.ew.modules.system.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/system/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@ApiOperation(value = "用户列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNumb",defaultValue = "1",value = "查询页码",required = false,paramType = "query"),
		@ApiImplicitParam(name = "pagSize",defaultValue = "10",value = "每页条数",required = false,paramType = "query"),
	})
	@GetMapping(path = "list")
	public ResultDto<IPage<User>> list(
				@RequestParam(name = "pageNumb",required = false,defaultValue = "1") Integer pageNumb,
				@RequestParam(name = "pagSize",required = false,defaultValue = "1") Integer pagSize
			){
		IPage<User> page = userService.page(new Page<User>(pageNumb, pagSize));
		return ResultDtoUtil.success(page);
	}
	
	@ApiOperation(value = "查询用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId",value = "用户标识",required = true,paramType = "path"),
	})
	@GetMapping(path = "/query/{userId}")
	public ResultDto<User> queryById(@PathVariable(name = "userId",required = true) Long userId){
		User user = userService.getById(userId);
		if (user == null) {
			return RequestError.business("用户标识有误");	
		}
		return ResultDtoUtil.success(user);
	}
	
	@ApiOperation(value = "添加用户信息")
	@PostMapping(path = "/add")
	public ResultDto<Boolean> add(User user){
		if (userService.save(user)) {
			return ResultDtoUtil.success(); 
		}
		return RequestError.business("添加失败");  
	}
	
	@ApiOperation(value = "更新用户信息")
	@PostMapping(path = "/update")
	public ResultDto<Boolean> update(User user){
		if (userService.updateById(user)) {
			return ResultDtoUtil.success(); 
		}
		return RequestError.business("更新失败");  
	}
	
	
	@ApiOperation(value = "删除用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId",value = "用户标识",required = true,paramType = "path"),
	})
	@PostMapping(path = "/delete/{userId}")
	public ResultDto<Boolean> delete(@PathVariable(name = "userId",required = true) Long userId){
		if (userService.removeById(userId)) {
			return ResultDtoUtil.success(); 
		}
		return RequestError.business("删除失败"); 
	}
	
	
}
