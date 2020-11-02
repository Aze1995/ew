package com.ew.modules.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.common.utils.WrapperUtil;
import com.ew.modules.system.entity.User;
import com.ew.modules.system.mapper.UserMapper;
import com.ew.modules.system.service.IUserService;
import com.ew.modules.system.vo.UserVo;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public IPage<UserVo> findUserInfo(IPage<User> page, String userName, String nickname, String phone) {
		LambdaQueryWrapper<User> wrapper = WrapperUtil.lambdaQuery(new User());
		if (StringUtils.isNotBlank(userName)) {
			wrapper.like(User::getUsername, userName);
		}
		if (StringUtils.isNotBlank(nickname)) {
			wrapper.like(User::getNickname, nickname);
		}
		if (StringUtils.isNotBlank(phone)) {
			wrapper.like(User::getPhone, phone);
		}
		return baseMapper.findUserInfo(page,wrapper);
	}


}
