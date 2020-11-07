package com.ew.modules.system.service;

import java.util.List;

import com.ew.modules.system.entity.Menu;
import com.ew.modules.system.vo.MenuVo;

public interface IMenuService {

	
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
	List<MenuVo> findMenuByPid(Long pid);

	/**
	 * 查询菜单详情
	 * @param id
	 * @return
	 */
	Menu getById(Long id);
	
	/**
	 * 保存菜单信息
	 * @param menu
	 * @return
	 */
	boolean save(Menu menu);
	
	/**
	 * 更新菜单信息
	 * @param menu
	 * @param id
	 * @return
	 */
	boolean updateById(Menu menu, Long id);

	/**
	 * 删除菜单信息
	 * @param id
	 * @return
	 */
	boolean removeById(Long id);

	
	
}
