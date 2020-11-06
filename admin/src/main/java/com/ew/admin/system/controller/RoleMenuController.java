package com.ew.admin.system.controller;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ew.common.dto.ResultDto;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.modules.system.service.IRoleMenuService;
import com.ew.modules.system.service.IRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "角色权限管理")
@Validated
@RestController
@RequestMapping("/system/roleMenu")
public class RoleMenuController {

	@Autowired
	private IRoleMenuService roleMenuService;
	@Autowired
	private IRoleService roleService; 

	@ApiOperation(value = "查询角色菜单权限")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "roleId", value = "角色标识", required = true, paramType = "path"), 
	})
	@ApiResponses({ @ApiResponse(code = 200, message = "data -> 角色拥有权限的菜单Id") })
	@RequiresPermissions(value = {"system:roleMenu:view"})
	@GetMapping(path = "/query/{roleId}")
	public ResultDto<List<Long>> query(
			@NotNull @Min(value = 1) @PathVariable(name = "roleId", required = true) Long roleId) {
		List<Long> menuIds = roleMenuService.findMenuIdByRoleId(roleId);
		return ResultDtoUtil.success(menuIds);
	}

	@ApiOperation(value = "编辑角色菜单权限")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Id", value = "角色标识", required = true, paramType = "path"), 
		@ApiImplicitParam(name = "menuIds", value = "菜单标识集", required = true, paramType = "body"), 
	})
	@RequiresPermissions(value = {"system:roleMenu:edit"})
	@PostMapping(path = "/edit/{Id}")
	public ResultDto<Boolean> edit(@NotNull @Min(value = 1) @PathVariable(name = "Id", required = true) Long roleId,
			@RequestBody @Validated @NotNull Set<Long> menuIds) {
		if (!roleService.verifyRoleId(roleId)) {
			return ResultDtoUtil.RequestError.business("角色标识有误");
		}
		if (roleMenuService.updateRoleMenu(roleId, menuIds)) {
			return ResultDtoUtil.success();
		}
		return ResultDtoUtil.RequestError.business("角色菜单权限更新失败");
	}

}
