package com.ew.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ew.modules.system.entity.Menu;
import com.ew.modules.system.vo.MenuVo;

public interface MenuMapper extends BaseMapper<Menu> {

	/**
	 * 查询菜单信息
	 * @param wrapper
	 * @return
	 */
	List<MenuVo> findMenuVo(@Param(Constants.WRAPPER) Wrapper<Menu> wrapper);
	
}
