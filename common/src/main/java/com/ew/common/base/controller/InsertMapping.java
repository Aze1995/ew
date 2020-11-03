package com.ew.common.base.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ew.common.base.IBaseService;
import com.ew.common.dto.ResultDto;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.common.utils.ResultDtoUtil.RequestError;

import io.swagger.annotations.ApiOperation;

/**
 * 新增接口
 * @author Mr`Huang
 * @Date 2020-11-3 15:47:38
 * @param <Service>
 * @param <Entity>
 * @param <Form>
 */
@Validated
public interface InsertMapping<Service extends IBaseService<Entity>, Entity, Form>
		extends BaseMapping<IBaseService<Entity>, Entity> {

	@ApiOperation(value = "添加")
	@PostMapping("add")
	default ResultDto<Boolean> add(@RequestBody @Validated Form form) {
		Entity entity = newInstance();
		BeanUtils.copyProperties(form,entity);
		if (getService().save(entity)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("添加失败");
	}
	
	/**
	 * 获取Entity实例
	 * @return
	 */
	Entity newInstance();
}
