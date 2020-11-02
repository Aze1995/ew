package com.ew.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ew.common.base.BaseEntity;
import com.ew.common.enums.StatusEnum;

public class WrapperUtil {

	/**
	 *   获取查询构造器<br/>
	 * 查询记录为未删除记录  
	 * @param <T>
	 * @return
	 */
	public static <T extends BaseEntity> LambdaQueryWrapper<T> lambdaQuery(T entity){
		return Wrappers.lambdaQuery(entity).eq(true, T::getStatus, StatusEnum.OK.getCode());
	}
	
}
