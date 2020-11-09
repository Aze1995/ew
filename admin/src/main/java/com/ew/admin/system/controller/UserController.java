package com.ew.admin.system.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ew.admin.system.form.UserForm;
import com.ew.common.Constant.DefaultConst;
import com.ew.common.dto.ResultDto;
import com.ew.common.enums.ActionLogEnum;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.common.utils.ResultDtoUtil.RequestError;
import com.ew.component.actionLog.action.UserActionSign;
import com.ew.component.actionLog.annotation.ActionLog;
import com.ew.component.shiro.ShiroUtil;
import com.ew.modules.system.entity.User;
import com.ew.modules.system.service.IUserService;
import com.ew.modules.system.vo.UserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户管理")
@Validated
@RestController
@RequestMapping("/system/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@ApiOperation(value = "添加用户")
	@RequiresPermissions(value = { "system:user:add" })
	@ActionLog(name = "添加用户",type = ActionLogEnum.SYSTEM)
	@PostMapping("add")
	public ResultDto<Boolean> add(@RequestBody @Validated UserForm form) {
		User entity = new User();
		BeanUtils.copyProperties(form, entity);
		entity.setPassword(DefaultConst.USER_PASSWORD);// 新增用户初始化密码
		if (userService.save(entity)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("添加失败");
	}

	@ApiOperation(value = "用户列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNumb", defaultValue = "1", value = "查询页码", required = false, paramType = "query"),
			@ApiImplicitParam(name = "pagSize", defaultValue = "10", value = "每页条数", required = false, paramType = "query"),
			@ApiImplicitParam(name = "userName", value = "登入名", required = false, paramType = "query"),
			@ApiImplicitParam(name = "nickname", value = "用户昵称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "phone", value = "手机号", required = false, paramType = "query"), })
	@RequiresPermissions(value = { "system:user:view" })
	@GetMapping(path = "list")
	public ResultDto<IPage<UserVo>> list(
			@RequestParam(name = "pageNumb", required = false, defaultValue = "1") Integer pageNumb,
			@RequestParam(name = "pagSize", required = false, defaultValue = "10") Integer pagSize, String userName,
			String nickname, String phone) {
		IPage<UserVo> userInfo = userService.findUserInfo(new Page<User>(pageNumb, pagSize), userName, nickname, phone);
		return ResultDtoUtil.success(userInfo);
	}

	@ApiOperation(value = "更新用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Id", value = "标识", required = true, paramType = "path"), 
	})
	@ActionLog(name = "更新用户信息",type = ActionLogEnum.SYSTEM)
	@RequiresPermissions(value = { "system:user:edit" })
	@PostMapping(path = "/edit/{Id}")
	public ResultDto<Boolean> edit(@RequestBody @Validated UserForm form,
			@NotNull @Min(value = 1) @PathVariable(name = "Id", required = true) Long Id) {
		User entity = new User();
		BeanUtils.copyProperties(form, entity);
		if (userService.updateById(entity, Id)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("更新失败");
	}

	@ApiOperation(value = "更新登入密码")
	@ApiImplicitParams({ 
			@ApiImplicitParam(name = "oldPassword", value = "源密码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "newPassowrd", value = "新密码", required = true, paramType = "query"), })
	@RequiresAuthentication//登入成功就有权限
	@ActionLog(name = "更新登入密码",type = ActionLogEnum.SYSTEM)
	@PostMapping(path = "/editPassWord")
	public ResultDto<Boolean> editPassWord( @NotBlank String oldPassword, // 原密码
											@NotBlank String newPassowrd) {// 新密码
		Long userId = ShiroUtil.getLoginUser().getUserId();
		if (!userService.verifyPassword(userId, oldPassword)) {
			return RequestError.business("源密码不正确");
		}
		if (userService.updateUserPassWord(userId, newPassowrd)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("源密码验证通过,更新失败");
	}

	@ApiOperation(value = "重置用户密码")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "userId", value = "用户标识", required = true, paramType = "path"), })
	@RequiresPermissions(value = { "system:user:edit" })
	@ActionLog(name = "重置用户密码",type = ActionLogEnum.SYSTEM,key = UserActionSign.RESET_PASSWORD,action = UserActionSign.class)
	@PostMapping(path = "/resetPassWord/{userId}")
	public ResultDto<Boolean> resetPassWord(
			@NotNull @Min(value = 1) @PathVariable(name = "userId", required = true) Long userId) {
		if (userId.equals(ShiroUtil.getLoginUser().getUserId())) {
			return RequestError.business("无法重置当前登入用户");
		}
		if (userId.equals(DefaultConst.SYSTEM_ADMIN_ID)) {
			return RequestError.badRequest("权限不足");
		}
		if (userService.updateUserPassWord(userId, DefaultConst.USER_PASSWORD)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("重置失败");
	}

	@ApiOperation(value = "删除用户", notes = "")
	@ApiImplicitParams({ @ApiImplicitParam(name = "Id", value = "标识", required = true, paramType = "path"), })
	@RequiresPermissions(value = { "system:user:del" })
	@ActionLog(name = "重置用户密码",type = ActionLogEnum.SYSTEM,key = UserActionSign.USER_DELETE,action = UserActionSign.class)
	@PostMapping(path = "/delete/{Id}")
	public ResultDto<Boolean> delete(@NotNull @Min(value = 1) @PathVariable(name = "Id", required = true) Long Id) {
		if (userService.removeById(Id)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("删除失败");
	}

	@ApiOperation(value = "用户详情")
	@ApiImplicitParams({ @ApiImplicitParam(name = "Id", value = "标识", required = true, paramType = "path"), })
	@RequiresPermissions(value = { "system:user:view" })
	@GetMapping(path = "/query/{Id}")
	public ResultDto<User> query(@NotNull @Min(value = 1) @PathVariable(name = "Id", required = true) Long Id) {
		User data = userService.getById(Id);
		if (data != null) {
			return ResultDtoUtil.success(data);
		}
		return RequestError.business("未找到用户");
	}

}
