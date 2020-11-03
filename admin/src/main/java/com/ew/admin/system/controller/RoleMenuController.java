package com.ew.admin.system.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ew.admin.system.form.RoleMenuForm;
import com.ew.common.dto.ResultDto;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.modules.system.service.IRoleMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "角色菜单权限管理")
@Validated
@RestController
@RequestMapping("/system/roleMenu")
public class RoleMenuController {

	@Autowired
	private IRoleMenuService roleMenuService;

	@ApiOperation(value = "查询角色菜单权限")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "roleId", value = "角色标识", required = true, paramType = "path"), 
	})
	@ApiResponses({
		@ApiResponse(code = 200,message="data -> 角色拥有权限的菜单Id")
	})
	@GetMapping(path = "/query/{roleId}")
	public ResultDto<List<Long>> query(
			@NotNull @Min(value = 1) @PathVariable(name = "roleId", required = true) Long roleId) {
		List<Long> menuIds = roleMenuService.findMenuIdByRoleId(roleId);
		return ResultDtoUtil.success(menuIds);
	}

	@ApiOperation(value = "编辑角色菜单权限")
	@PostMapping(path = "/edit")
	public ResultDto<Boolean> edit(@RequestBody @Validated RoleMenuForm form) {
		Long roleId = form.getRoleId();
		Collection<Long> menuIds = form.getMenuIds();
		if (roleMenuService.updateRoleMenu(roleId, menuIds)) {
			return ResultDtoUtil.success();
		}
		return ResultDtoUtil.RequestError.business("角色菜单权限更新失败");
	}

}
