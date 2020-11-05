package com.ew.modules.system.service;

import com.ew.common.base.IBaseService;
import com.ew.modules.system.entity.ActionLog;

/**
 * 
 * @author Mr`Huang
 * @Date 2020-10-30 18:20:30
 */
public interface IActionLogService extends IBaseService<ActionLog> {

	@Override
	default boolean updateById(ActionLog entity, Long id) {
		entity.setId(id);
		return updateById(entity);
	}
	
	/**
	 * 异步插入信息
	 * @param log
	 */
	void saveAsync(ActionLog log);
	
}
