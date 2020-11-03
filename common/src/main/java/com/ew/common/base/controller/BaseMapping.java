package com.ew.common.base.controller;

import com.ew.common.base.IBaseService;

/**
 * 基类接口
 * @author Mr`Huang
 * @Date 2020-11-3 15:34:25
 * @param <Service>
 * @param <Entity>
 */
public interface BaseMapping<Service extends IBaseService<Entity>,Entity> {

	/**
	 * 获取具体执行Service
	 * @return
	 */
	Service getService();
	
	/**
	 * 获取Entity实例
	 * @return
	 */
	default	Entity newInstance() {
		return null;
	}
}
