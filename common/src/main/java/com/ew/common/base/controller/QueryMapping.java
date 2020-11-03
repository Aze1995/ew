package com.ew.common.base.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ew.common.base.IBaseService;
import com.ew.common.dto.ResultDto;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.common.utils.ResultDtoUtil.RequestError;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 查询接口
 * @author Mr`Huang
 * @Date 2020-11-3 15:44:11
 * @param <Service>
 * @param <Entity>
 */
@Validated
public interface QueryMapping<Service extends IBaseService<Entity>, Entity>
		extends BaseMapping<IBaseService<Entity>, Entity> {
	
	@ApiOperation(value = "列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNumb",defaultValue = "1",value = "查询页码",required = false,paramType = "query"),
		@ApiImplicitParam(name = "pagSize",defaultValue = "10",value = "每页条数",required = false,paramType = "query"),
	})
	@GetMapping(path = "list")
	default ResultDto<IPage<Entity>> list(
			@Min(value = 1) @RequestParam(name = "pageNumb",required = false,defaultValue = "1") Integer pageNumb,
			@Min(value = 1)	@RequestParam(name = "pagSize",required = false,defaultValue = "10") Integer pagSize
			){
		IPage<Entity> userInfo = getService().page(new Page<Entity>(pageNumb, pagSize));
		return ResultDtoUtil.success(userInfo);
	}
	
	@ApiOperation(value = "详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Id",value = "标识",required = true,paramType = "path"),
	})
	@GetMapping(path = "/query/{Id}")
	default ResultDto<Entity> query(@NotNull @Min(value = 1) @PathVariable(name = "Id",required = true) Long Id) {
		Entity data = getService().getById(Id);
		if (data != null) {
			return ResultDtoUtil.success(data);
		}
		return RequestError.business();
	}
}
