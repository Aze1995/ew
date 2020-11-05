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
		
		/**
		 * 请求参数不合法
		 * @param <T>
		 * @param message	描述信息
		 * @return
		 */
		public static <T> ResultDto<T> parameter(String message){
			return setDtoMessage(parameter(), message);
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
			return setDtoMessage(business(), message);
		}
		
		/**请求权限不足*/
		public static <T> ResultDto<T> badRequest(){
			return new ResultDto<T>(ResultEnum.REQUEST_ERROR_BAD_REQUEST,null);
		}
		
		/**
		 * 请求权限不足
		 * @param <T>
		 * @param message
		 * @return
		 */
		public static <T> ResultDto<T> badRequest(String message){
			return setDtoMessage(badRequest(),message);
		}
		
	}
	
	/**
	 *  系统异常
	 * @author Mr`Huang
	 * @Date 2020-11-2 18:04:15
	 */
	public static class SystemError{
		
		/**
		 *   系统未定义异常
		 * @param <T>
		 * @return
		 */
		public static <T> ResultDto<T> undefined(){
			return new ResultDto<T>(ResultEnum.SYSTEM_ERROR_UNDEFINED,null);
		}
	}
	
	/**
	 * 自定义响应消息
	 * @param <T>
	 * @param dto			dto
	 * @param message		描述消息
	 * @return
	 */
	private static <T> ResultDto<T> setDtoMessage(ResultDto<T> dto,String message){
		dto.setMessage(message);
		return dto;
	}
}
