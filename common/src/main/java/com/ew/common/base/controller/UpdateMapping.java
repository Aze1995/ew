package com.ew.common.base.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ew.common.base.IBaseService;
import com.ew.common.dto.ResultDto;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.common.utils.ResultDtoUtil.RequestError;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *  编辑接口
 * @author Mr`Huang
 * @Date 2020-11-3 15:38:38
 * @param <Service>
 * @param <Entity>
 * @param <Form>
 */
@Validated
public interface UpdateMapping<Service extends IBaseService<Entity>, Entity, Form>
		extends BaseMapping<IBaseService<Entity>, Entity> {

	@ApiOperation(value = "编辑")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Id",value = "标识",required = true,paramType = "path"),
	})
	@PostMapping(path = "/edit/{Id}")
	default ResultDto<Boolean> edit(@RequestBody @Validated Form form,
			@NotNull @Min(value = 1) @PathVariable(name = "Id",required = true) Long Id) {
		Entity entity = newInstance();
		BeanUtils.copyProperties(form,entity);
		if (getService().updateById(entity, Id)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("更新失败");
	}
	
	/**
	 * 	获取Entity实例
	 * @return
	 */
	Entity newInstance();
}
