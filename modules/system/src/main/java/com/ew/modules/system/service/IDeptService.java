package com.ew.modules.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ew.modules.system.entity.Dept;

public interface IDeptService extends IService<Dept>{

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
