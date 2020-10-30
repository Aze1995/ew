package com.ew.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @program: ew
 * @author: ZeQun
 * @create: 2020-10-30 16:47
 */
public class HttpServletUtil {

	/**
	 * 获取ServletRequestAttributes对象
	 */
	public static ServletRequestAttributes getServletRequest() {
		return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	}

	/**
	 * 获取HttpServletRequest对象
	 */
	public static HttpServletRequest getRequest() {
		return getServletRequest().getRequest();
	}

	/**
	 * 获取HttpServletResponse对象
	 */
	public static HttpServletResponse getResponse() {
		return getServletRequest().getResponse();
	}

	/**
	 * 获取请求参数
	 */
	public static String getParameter(String param) {
		return getRequest().getParameter(param);
	}

}