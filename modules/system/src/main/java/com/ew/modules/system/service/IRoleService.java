package com.ew.modules.system.service;

import com.ew.common.base.IBaseService;
import com.ew.modules.system.entity.Role;

/**
 * 
 * @author Mr`Huang
 * @Date 2020年10月31日 下午1:03:26
 */
public interface IRoleService extends IBaseService<Role>{

	@Override
	default boolean updateById(Role entity, Long id) {
		entity.setRoleId(id);
		return updateById(entity);
	}
	
	/**
	 * 角色id是否正确
	 * @param roleId
	 * @return
	 */
	boolean verifyRoleId(Long roleId);
	
}
