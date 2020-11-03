package com.ew.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ew.common.dto.ResultDto;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.modules.system.entity.Dept;
import com.ew.modules.system.service.IDeptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "部门管理")
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
	
	
}
