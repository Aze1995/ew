package com.ew.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ew.common.base.IBaseService;
import com.ew.modules.system.entity.User;
import com.ew.modules.system.vo.UserVo;

/**
 * 用户业务接口
 * @author Mr`Huang
 * @Date 2020年10月31日 下午5:55:29
 */
public interface IUserService extends IBaseService<User> {

	@Override
	default boolean updateById(User entity, Long id) {
		entity.setUserId(id);
		return updateById(entity);
	}
	
	/**
	 *	查询用户信息
	 * @param page		分页信息
	 * @param userName	登入用户名
	 * @param nickname	用户昵称
	 * @param phone		手机号码
	 * @return
	 */
	IPage<UserVo> findUserInfo(IPage<User> page,String userName,String nickname,String phone);
	
	/**
	 * 验证密码
	 * @param userId		用户标识
	 * @param password		登入密码
	 * @return
	 */
	boolean verifyPassword(Long userId,String password);
	
	/**
	 *	更新用户密码
	 * @param userId	用户标识
	 * @param password	新密码
	 * @return
	 */
	boolean updateUserPassWord(Long userId,String password);
	
}
