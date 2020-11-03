package com.ew.common.base;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ew.common.dto.ResultDto;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.common.utils.ResultDtoUtil.RequestError;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Controller 基类实现增删改接口
 * @author Mr`Huang
 * @Date 2020-11-3 14:42:43
 * @param <Service>		Service接口
 * @param <Entity>		实体
 * @param <Form>		验证表单
 */
@Validated
public abstract class BaseAbstractController<Service extends IBaseService<Entity>,Entity,Form> {

	@Autowired
	protected Service baseService;
	
	@ApiOperation(value = "列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNumb",defaultValue = "1",value = "查询页码",required = false,paramType = "query"),
		@ApiImplicitParam(name = "pagSize",defaultValue = "10",value = "每页条数",required = false,paramType = "query"),
	})
	@GetMapping(path = "list")
	public ResultDto<IPage<Entity>> list(
				@RequestParam(name = "pageNumb",required = false,defaultValue = "1") Integer pageNumb,
				@RequestParam(name = "pagSize",required = false,defaultValue = "10") Integer pagSize
			){
		IPage<Entity> userInfo = baseService.page(new Page<Entity>(pageNumb, pagSize));
		return ResultDtoUtil.success(userInfo);
	}
	
	@ApiOperation(value = "详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Id",value = "标识",required = true,paramType = "path"),
	})
	@GetMapping(path = "/query/{Id}")
	public ResultDto<Entity> query(@NotNull @Min(value = 1) @PathVariable(name = "Id",required = true) Long Id) {
		Entity data = baseService.getById(Id);
		if (data != null) {
			return ResultDtoUtil.success(data);
		}
		return RequestError.business();
	}
	
	@ApiOperation(value = "删除")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Id",value = "标识",required = true,paramType = "path"),
	})
	@PostMapping(path = "/delete/{Id}")
	public ResultDto<Boolean> delete(@NotNull @Min(value = 1) @PathVariable(name = "Id",required = true) Long Id) {
		if (baseService.removeById(Id)) {
			return ResultDtoUtil.success(); 
		}
		return RequestError.business("删除失败");
	}
	
	@ApiOperation(value = "添加")
	@PostMapping("add")
	public ResultDto<Boolean> add(@RequestBody @Validated Form form) {
		Entity entity = newInstance();
		BeanUtils.copyProperties(form,entity);
		if (baseService.save(entity)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("添加失败");
	}
	
	@ApiOperation(value = "编辑")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Id",value = "标识",required = true,paramType = "path"),
	})
	@PostMapping(path = "/edit/{Id}")
	public ResultDto<Boolean> edit(@RequestBody @Validated Form form,
			@NotNull @Min(value = 1) @PathVariable(name = "Id",required = true) Long Id) {
		Entity entity = newInstance();
		BeanUtils.copyProperties(form,entity);
		if (baseService.updateById(entity, Id)) {
			return ResultDtoUtil.success();
		}
		return RequestError.business("更新失败");
	}
	
	/**
	 * 获取Entity实例
	 * @return
	 */
	protected abstract Entity newInstance();
	
}
