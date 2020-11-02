package com.ew.modules.system.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ew.modules.system.entity.User;
import com.ew.modules.system.vo.UserVo;

public interface UserMapper extends BaseMapper<User> {

	/**
	 * 查询用户信息
	 * @param page		分页条件
	 * @param wrapper
	 * @return
	 */
	IPage<UserVo> findUserInfo(IPage<User> page,@Param(Constants.WRAPPER) Wrapper<User> wrapper);
	
}
