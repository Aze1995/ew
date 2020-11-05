package com.ew.modules.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.common.utils.WrapperUtil;
import com.ew.modules.system.entity.Role;
import com.ew.modules.system.mapper.RoleMapper;
import com.ew.modules.system.service.IRoleService;

/**
 * 
 * @author Mr`Huang
 * @Date 2020年10月31日 下午1:03:21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

	@Override
	public boolean verifyRoleId(Long roleId) {
		LambdaQueryWrapper<Role> lambdaQuery = WrapperUtil.lambdaQuery(new Role());
		lambdaQuery.eq(Role::getRoleId,	roleId);
		return super.count(lambdaQuery) > 0;
	}

}
