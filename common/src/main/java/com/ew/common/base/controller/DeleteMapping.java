package com.ew.common.base.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ew.common.base.IBaseService;
import com.ew.common.dto.ResultDto;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.common.utils.ResultDtoUtil.RequestError;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 删除接口
 * @author Mr`Huang
 * @Date 2020-11-3 15:43:14
 * @param <Service>
 * @param <Entity>
 */
@Validated
public interface DeleteMapping<Service extends IBaseService<Entity>, Entity>
		extends BaseMapping<IBaseService<Entity>, Entity> {

	@ApiOperation(value = "删除")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Id",value = "标识",required = true,paramType = "path"),
	})
	@PostMapping(path = "/delete/{Id}")
	default ResultDto<Boolean> delete(@NotNull @Min(value = 1) @PathVariable(name = "Id",required = true) Long Id) {
		if (getService().removeById(Id)) {
			return ResultDtoUtil.success(); 
		}
		return RequestError.business("删除失败");
	}
	
}
