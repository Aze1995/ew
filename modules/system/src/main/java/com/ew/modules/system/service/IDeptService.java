package com.ew.modules.system.service;

import java.util.List;

import com.ew.common.base.IBaseService;
import com.ew.modules.system.entity.Dept;

public interface IDeptService extends IBaseService<Dept>{

	default boolean updateById(Dept entity,Long id) {
		entity.setDeptId(id);
		return updateById(entity);
	}
	
	/**
	 * 查询所有父级部门
	 * @return
	 */
	List<Dept> findParentDept();
	
	/**
	 * 查询部门信息
	 * @param pid	父级标识
	 * @return
	 */
	List<Dept> findByPid(Long pid);
	
}
