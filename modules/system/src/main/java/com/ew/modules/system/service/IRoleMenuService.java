package com.ew.modules.system.service;

import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ew.modules.system.entity.RoleMenu;

public interface IRoleMenuService extends IService<RoleMenu> {

	/**
	 * 更新角色菜单权限
	 * @param roleId	角色标识
	 * @param menuIds
	 * @return
	 */
	boolean updateRoleMenu(Long roleId,Collection<Long> menuIds);

	/**
	 * 查询菜单标识
	 * @param roleId	角色标识
	 * @return
	 */
	List<Long> findMenuIdByRoleId(Long roleId);
}
