package com.ew.admin.system.controller;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ew.admin.system.form.MenuForm;
import com.ew.common.dto.ResultDto;
import com.ew.common.enums.ActionLogEnum;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.common.utils.ResultDtoUtil.RequestError;
import com.ew.component.actionLog.annotation.ActionLog;
import com.ew.modules.system.entity.Menu;
import com.ew.modules.system.service.IMenuService;
import com.ew.modules.system.vo.MenuVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 系统-菜单 前端控制器
 * </p>
 *
 * @author Hr`Huang
 * @since 2020-11-05
 */
@Api(tags = "菜单管理")
@Validated
@RestController
@RequestMapping("/system/menu")
public class MenuController {

	@Autowired
	private IMenuService service;
	
	@ApiOperation(value = "菜单列表")
	@RequiresPermissions(value = {"system:menu:view"})
	@GetMapping(path = "list")
	public ResultDto<List<MenuVo>> list(){
		return ResultDtoUtil.success(service.findAll());
	}

	@ApiOperation(value = "菜单详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Id",value = "标识",required = true,paramType = "path"),
	})
	@RequiresPermissions(value = {"system:menu:view"})
	@GetMapping(path = "/query/{Id}")
	public ResultDto<Menu> query(@NotNull @Min(value = 1) @PathVariable(name = "Id",required = true) Long Id) {
		Menu data = service.getById(Id);
		if (data != null) {
			return ResultDtoUtil.success(data);
		}
		return RequestError.business();
	}
	
	@ApiOperation(value = "添加菜单",notes = "")
	@RequiresPermissions(value = {"system:menu:add"})
	@ActionLog(name = "添加菜单",type = ActionLogEnum.SYSTEM)
	@PostMapping("add")
	public ResultDto<Boolean> add(@RequestBody @Validated MenuForm form) {
		Menu entity = new Menu();
		BeanUtils.copyProperties(form,entity);
		if (service.save(entity)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("添加失败");
	}

	@ApiOperation(value = "编辑菜单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Id",value = "标识",required = true,paramType = "path"),
	})
	@RequiresPermissions(value = {"system:menu:edit"})
	@ActionLog(name = "编辑菜单",type = ActionLogEnum.SYSTEM)
	@PostMapping(path = "/edit/{Id}")
	public ResultDto<Boolean> edit(@RequestBody @Validated MenuForm form,
			@NotNull @Min(value = 1) @PathVariable(name = "Id",required = true) Long Id) {
		Menu entity = new Menu();
		BeanUtils.copyProperties(form,entity);
		if (service.updateById(entity, Id)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("更新失败");
	}	

	@ApiOperation(value = "删除菜单",notes = "")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Id",value = "标识",required = true,paramType = "path"),
	})
	@ActionLog(name = "删除菜单",type = ActionLogEnum.SYSTEM)
	@RequiresPermissions(value = {"system:menu:del"})
	@PostMapping(path = "/delete/{Id}")
	public ResultDto<Boolean> delete(@NotNull @Min(value = 1) @PathVariable(name = "Id",required = true) Long Id) {
		if (service.removeById(Id)) {
			return ResultDtoUtil.success(); 
		}
		return RequestError.business("删除失败");
	}

}

