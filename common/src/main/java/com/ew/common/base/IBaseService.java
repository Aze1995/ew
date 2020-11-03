package com.ew.common.base;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Service 接口基类
 * @author Mr`Huang
 * @Date 2020-11-3 14:02:49
 * @param <T>
 */
public interface IBaseService<T> extends IService<T> {

	/**
	 * 通过Id更新实例
	 * @param entit
	 * @param id		
	 * @return
	 */
	boolean updateById(T entity,Long id);
	
}
