package com.ew.modules.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ew.modules.system.entity.Menu;
import com.ew.modules.system.vo.MenuVo;

public interface IMenuService extends IService<Menu>{

	/**
	 * 查询所有菜单
	 * @return
	 */
	List<MenuVo> findAll();
	
	/**
	 * 查询所有子级菜单
	 * @param pid	父级菜单标识
	 * @return
	 */
	List<MenuVo> findByPid(Long pid);
}
