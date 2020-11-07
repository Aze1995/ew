package com.ew.modules.system.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.modules.system.entity.RoleMenu;
import com.ew.modules.system.mapper.RoleMenuMapper;
import com.ew.modules.system.service.IMenuService;
import com.ew.modules.system.service.IRoleMenuService;
import com.ew.modules.system.vo.MenuVo;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

	@Autowired
	private IMenuService menuService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateRoleMenu(Long roleId, Collection<Long> menuIds) {
		LambdaUpdateWrapper<RoleMenu> wrapper = Wrappers.<RoleMenu>lambdaUpdate().eq(RoleMenu::getRoleId, roleId);
		baseMapper.delete(wrapper);// 删除原来的权限
		if (!menuIds.isEmpty()) {
			List<RoleMenu> roleMenus = menuIds.stream().map(menuId -> {
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setMenuId(menuId);// 菜单标识
				roleMenu.setRoleId(roleId);// 角色标识
				return roleMenu;
			}).collect(Collectors.toList());
			return super.saveBatch(roleMenus);// 重新插入权限
		}
		return true;
	}

	@Override
	public List<Long> findMenuIdByRoleId(Long roleId) {
		List<RoleMenu> menus = lambdaQuery().select(RoleMenu::getMenuId).eq(RoleMenu::getRoleId, roleId).list();
		return menus.stream().map(m -> {
			return m.getMenuId();
		}).collect(Collectors.toList());
	}

	@Override
	public List<MenuVo> findMenuByRoleId(Long roleId) {
		List<Long> memuIds = findMenuIdByRoleId(roleId);
		List<MenuVo> menuVos = menuService.findAll();//菜单使用深拷贝
		setRoleMenus(menuVos, memuIds);
		return menuVos;
	}
	
	/**
	 * 设置角色菜单
	 * @param menus		菜单列表
	 * @param memuIds	菜单标识集合
	 */
	void setRoleMenus(List<MenuVo> menus,List<Long> memuIds) {
		if (CollectionUtils.isEmpty(menus)) {
			return;
		}
		for (int i = 0; i < menus.size(); i++) {
			MenuVo menuVo = menus.get(i);
			Long menuId = menuVo.getMenuId();
			if (memuIds.contains(menuId)) {
				memuIds.remove(menuId);
				setRoleMenus(menuVo.getMenu(), memuIds);
			}else {
				menus.remove(i--);
			}
		}
	}
	
}
