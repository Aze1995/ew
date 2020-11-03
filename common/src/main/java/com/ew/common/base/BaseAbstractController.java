package com.ew.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import com.ew.common.base.controller.DeleteMapping;
import com.ew.common.base.controller.InsertMapping;
import com.ew.common.base.controller.QueryMapping;
import com.ew.common.base.controller.UpdateMapping;

/**
 * Controller 基类实现增删改接口
 * @author Mr`Huang
 * @Date 2020-11-3 14:42:43
 * @param <Service>		Service接口
 * @param <Entity>		实体
 * @param <Form>		验证表单
 */
@Validated
public abstract class BaseAbstractController <Service extends IBaseService<Entity>,Entity,Form> implements
											InsertMapping<Service, Entity, Form>,//新增接口
											UpdateMapping<Service, Entity, Form>,//编辑接口
											DeleteMapping<Service, Entity>,//删除接口
											QueryMapping<Service, Entity>{//查询接口

	@Autowired
	protected Service baseService;

	@Override
	public IBaseService<Entity> getService() {
		return baseService;
	}

}
