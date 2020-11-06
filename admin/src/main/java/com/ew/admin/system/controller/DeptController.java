package com.ew.admin.system.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ew.admin.system.form.DeptForm;
import com.ew.common.dto.ResultDto;
import com.ew.common.enums.ActionLogEnum;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.common.utils.ResultDtoUtil.RequestError;
import com.ew.component.actionLog.annotation.ActionLog;
import com.ew.modules.system.entity.Dept;
import com.ew.modules.system.service.IDeptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 系统-部门 前端控制器
 * </p>
 *
 * @author Hr`Huang
 * @since 2020-11-05
 */
@Api(tags = "部门管理")
@Validated
@RestController
@RequestMapping("/system/dept")
public class DeptController {

	@Autowired
	private IDeptService service;
	
	@ApiOperation(value = "部门列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNumb",defaultValue = "1",value = "查询页码",required = false,paramType = "query"),
		@ApiImplicitParam(name = "pagSize",defaultValue = "10",value = "每页条数",required = false,paramType = "query"),
	})
	@RequiresPermissions(value = { "system:dept:view" })
	@GetMapping(path = "list")
	public ResultDto<IPage<Dept>> list(
				@RequestParam(name = "pageNumb",required = false,defaultValue = "1") Integer pageNumb,
				@RequestParam(name = "pagSize",required = false,defaultValue = "10") Integer pagSize
			){
		IPage<Dept> page = service.page(new Page<Dept>(pageNumb, pagSize));
		return ResultDtoUtil.success(page);
	}

	@ApiOperation(value = "部门详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Id",value = "标识",required = true,paramType = "path"),
	})
	@RequiresPermissions(value = { "system:dept:view" })
	@GetMapping(path = "/query/{Id}")
	public ResultDto<Dept> query(@NotNull @Min(value = 1) @PathVariable(name = "Id",required = true) Long Id) {
		Dept data = service.getById(Id);
		if (data != null) {
			return ResultDtoUtil.success(data);
		}
		return RequestError.business();
	}
	
	@ApiOperation(value = "添加部门",notes = "")
	@RequiresPermissions(value = { "system:dept:add" })
	@ActionLog(name = "添加部门",type = ActionLogEnum.SYSTEM)
	@PostMapping("add")
	public ResultDto<Boolean> add(@RequestBody @Validated DeptForm form) {
		Dept entity = new Dept();
		BeanUtils.copyProperties(form,entity);
		if (service.save(entity)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("添加失败");
	}

	@ApiOperation(value = "编辑部门")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Id",value = "标识",required = true,paramType = "path"),
	})
	@RequiresPermissions(value = { "system:dept:edit" })
	@ActionLog(name = "编辑部门",type = ActionLogEnum.SYSTEM)
	@PostMapping(path = "/edit/{Id}")
	public ResultDto<Boolean> edit(@RequestBody @Validated DeptForm form,
			@NotNull @Min(value = 1) @PathVariable(name = "Id",required = true) Long Id) {
		Dept entity = new Dept();
		BeanUtils.copyProperties(form,entity);
		if (service.updateById(entity, Id)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("更新失败");
	}	

	@ApiOperation(value = "删除部门",notes = "删除父级部门会级联删除所有关联子级部门")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Id",value = "标识",required = true,paramType = "path"),
	})
	@RequiresPermissions(value = { "system:dept:del" })
	@ActionLog(name = "删除部门",type = ActionLogEnum.SYSTEM)
	@PostMapping(path = "/delete/{Id}")
	public ResultDto<Boolean> delete(@NotNull @Min(value = 1) @PathVariable(name = "Id",required = true) Long Id) {
		if (service.removeById(Id)) {
			return ResultDtoUtil.success(); 
		}
		return RequestError.business("删除失败");
	}

}
