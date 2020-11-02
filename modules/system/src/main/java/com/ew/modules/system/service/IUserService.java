package com.ew.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ew.modules.system.entity.User;
import com.ew.modules.system.vo.UserVo;

/**
 * 用户业务接口
 * @author Mr`Huang
 * @Date 2020年10月31日 下午5:55:29
 */
public interface IUserService extends IService<User> {

	/**
	 * 	查询用户信息
	 * @param page		分页信息
	 * @param userName	登入用户名
	 * @param nickname	用户昵称
	 * @param phone		手机号码
	 * @return
	 */
	IPage<UserVo> findUserInfo(IPage<User> page,String userName,String nickname,String phone);
	
}
