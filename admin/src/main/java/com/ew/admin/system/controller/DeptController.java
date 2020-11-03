package com.ew.admin.system.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
import com.ew.common.utils.ResultDtoUtil;
import com.ew.common.utils.ResultDtoUtil.RequestError;
import com.ew.modules.system.entity.Dept;
import com.ew.modules.system.service.IDeptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "部门管理")
@Validated
@RestController
@RequestMapping("/system/dept")
public class DeptController {

	@Autowired
	private IDeptService deptService;
	
	@ApiOperation(value = "部门列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNumb",defaultValue = "1",value = "查询页码",required = false,paramType = "query"),
		@ApiImplicitParam(name = "pagSize",defaultValue = "10",value = "每页条数",required = false,paramType = "query"),
	})
	@GetMapping(path = "list")
	public ResultDto<IPage<Dept>> list(
				@RequestParam(name = "pageNumb",required = false,defaultValue = "1") Integer pageNumb,
				@RequestParam(name = "pagSize",required = false,defaultValue = "10") Integer pagSize
			){
		IPage<Dept> userInfo = deptService.page(new Page<Dept>(pageNumb, pagSize));
		return ResultDtoUtil.success(userInfo);
	}
	
	@ApiOperation(value = "部门详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deptId",value = "部门标识",required = true,paramType = "path"),
	})
	@GetMapping(path = "/query/{deptId}")
	public ResultDto<Dept> query(@NotNull @Min(value = 1) @PathVariable(name = "deptId",required = true) Long deptId) {
		Dept dept = deptService.getById(deptId);
		if (dept != null) {
			return ResultDtoUtil.success(dept);
		}
		return RequestError.business();
	}
	
	@ApiOperation(value = "添加部门")
	@PostMapping("add")
	public ResultDto<Boolean> add(@RequestBody @Validated DeptForm deptForm) {
		Dept dept = new Dept();
		BeanUtils.copyProperties(deptForm,dept);
		if (deptService.save(dept)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("添加失败");
	}

	@ApiOperation(value = "编辑部门信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deptId",value = "部门标识",required = true,paramType = "path"),
	})
	@PostMapping(path = "/edit/{deptId}")
	public ResultDto<Boolean> edit(@RequestBody @Validated DeptForm deptForm,
			@NotNull @Min(value = 1) @PathVariable(name = "deptId",required = true) Long deptId) {
		Dept dept = new Dept();
		BeanUtils.copyProperties(deptForm,dept);
		dept.setDeptId(deptId);
		if (deptService.updateById(dept)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("更新失败");
	}
	
	@ApiOperation(value = "删除部门信息",notes = "删除父级部门会级联删除所有关联子级部门")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deptId",value = "部门标识",required = true,paramType = "path"),
	})
	@PostMapping(path = "/delete/{deptId}")
	public ResultDto<Boolean> delete(@NotNull @Min(value = 1) @PathVariable(name = "deptId",required = true) Long deptId) {
		if (deptService.removeById(deptId)) {
			return ResultDtoUtil.success(); 
		}
		return RequestError.business("删除失败");
	}
	
	
}
