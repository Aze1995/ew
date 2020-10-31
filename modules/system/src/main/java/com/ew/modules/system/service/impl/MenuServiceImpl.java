package com.ew.modules.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.common.enums.StatusEnum;
import com.ew.modules.system.entity.Menu;
import com.ew.modules.system.mapper.MenuMapper;
import com.ew.modules.system.service.IMenuService;
import com.ew.modules.system.vo.MenuVo;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

	public List<MenuVo> findAll() {
		List<MenuVo> stairMenu = findByPid(0L);//查询一级菜单
		for (MenuVo menuVo : stairMenu) {
			List<MenuVo> second = findByPid(menuVo.getMenuId());//查询二级菜单
			menuVo.setMenu(second);
		}
		return stairMenu;
	}

	public List<MenuVo> findByPid(Long pid) {
		LambdaQueryWrapper<Menu> queryWrapper = Wrappers.<Menu>lambdaQuery();
		queryWrapper
			.eq(true, Menu::getStatus, StatusEnum.OK.getCode())
			.eq(true, Menu::getPid, pid)
			.orderByAsc(Menu::getSort);
		return baseMapper.findMenuVo(queryWrapper);
	}

}
