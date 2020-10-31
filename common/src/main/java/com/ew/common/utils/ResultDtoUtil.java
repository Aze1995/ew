package com.ew.common.utils;

import com.ew.common.dto.ResultDto;
import com.ew.common.enums.ResultEnum;

/**
 * Web响应工具
 * @author Mr`Huang
 * @Date 2020年10月31日 下午3:48:02
 */
public class ResultDtoUtil {

	/**
	 * 响应成功
	 * @param <T>
	 * @param data	响应内容
	 * @return
	 */
	public static <T> ResultDto<T> success(T data){
		return new ResultDto<T>(ResultEnum.SUCCESS,data);
	}
	
	/**响应成功*/
	public static <T> ResultDto<T> success(){
		return success(null);
	}
	
	/**
	 * 请求错误
	 * @author Mr`Huang
	 * @Date 2020年10月31日 下午6:42:46
	 */
	public static class RequestError{
		
		/**请求参数不合法*/
		public static <T> ResultDto<T> parameter(){
			return new ResultDto<T>(ResultEnum.REQUEST_ERROR_PARAMETER,null);
		}
		
		/**请求成功执行业务失败*/
		public static <T> ResultDto<T> business(){
			return new ResultDto<T>(ResultEnum.REQUEST_ERROR_BUSINESS,null);
		}
		
		/**
		 * 请求成功执行业务失败
		 * @param <T>
		 * @param message	响应描述
		 * @return
		 */
		public static <T> ResultDto<T> business(String message){
			ResultDto<T> business = business();
			business.setMessage(message);
			return business;
		}
		
	}
	
}
