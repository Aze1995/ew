package com.ew.common.utils;

import com.ew.common.dto.ResultDto;
import com.ew.common.enums.ResultEnum;

/**
 * Web响应工具
 * @author Mr`Huang
 * @Date 2020年10月31日 下午3:48:02
 */
public class ResultDtoUtil {

	/**响应成功*/
	public static <T> ResultDto<T> success(T obj){
		return new ResultDto<T>(ResultEnum.SUCCESS,obj);
	}
	
	/**响应成功*/
	public static <T> ResultDto<T> success(){
		return success(null);
	}
	
}
