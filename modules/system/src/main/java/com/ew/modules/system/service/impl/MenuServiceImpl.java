package com.ew.modules.system.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.common.Constant.CacheName;
import com.ew.common.Constant.DefaultConst;
import com.ew.common.utils.WrapperUtil;
import com.ew.modules.system.entity.Menu;
import com.ew.modules.system.mapper.MenuMapper;
import com.ew.modules.system.service.IMenuService;
import com.ew.modules.system.vo.MenuVo;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

	@Override
	@Cacheable(cacheNames = CacheName.MENU, key = CacheKey.LIST, unless = "#result == null ")
	public List<MenuVo> findAll() {
		List<MenuVo> stairMenu = findMenuByPid(DefaultConst.TOP_LEVE_PID);// 查询一级菜单
		for (MenuVo menuVo : stairMenu) {
			List<MenuVo> second = findMenuByPid(menuVo.getMenuId());// 查询二级菜单
			menuVo.setMenu(second);
			second.forEach(menu -> {
				List<MenuVo> thirdly = findMenuByPid(menuVo.getMenuId());// 查询三级-菜单权限
				menu.setMenu(thirdly);
			});
		}
		return stairMenu;
	}

	@Override
	@Caching(put = {
			@CachePut(cacheNames = CacheName.MENU, key = CacheKey.ID + "#id", unless = "#result == null ") }, evict = {
					@CacheEvict(cacheNames = CacheName.MENU, key = CacheKey.PID + "#menu.menuId"),
					@CacheEvict(cacheNames = CacheName.MENU, key = CacheKey.LIST), })
	public boolean save(Menu entity) {
		return super.save(entity);
	}

	@Override
	@Cacheable(cacheNames = CacheName.MENU, key = CacheKey.PID + "#pid", unless = "#result == null ")
	public List<MenuVo> findMenuByPid(Long pid) {
		LambdaQueryWrapper<Menu> queryWrapper = WrapperUtil.lambdaQuery(new Menu()).eq(true, Menu::getPid, pid)
				.orderByAsc(Menu::getSort);
		return baseMapper.findMenuVo(queryWrapper);
	}

	@Override
	@CachePut(cacheNames = CacheName.MENU, key = CacheKey.ID + "#id", unless = "#result == null ")
	public boolean updateById(Menu menu, Long id) {
		menu.setMenuId(id);
		return super.updateById(menu);
	}

	@Override
	@Cacheable(cacheNames = CacheName.MENU, key = CacheKey.ID + "#id", unless = "#result == null ")
	public Menu getById(Long id) {
		return super.getById(id);
	}

	@Override
	@Caching(evict = { @CacheEvict(cacheNames = CacheName.MENU, key = CacheKey.ID + "#id"),
			@CacheEvict(cacheNames = CacheName.MENU, key = CacheKey.PID + "#id"),
			@CacheEvict(cacheNames = CacheName.MENU, key = CacheKey.LIST), })
	public boolean removeById(Long id) {
		return super.removeById(id);
	}

	/**缓存Key*/
	class CacheKey {
		static final String LIST = "'list'";
		static final String ID = "'id-'+";
		static final String PID = "'PID-'+";
	}

}
