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

	/**
	 * 获取请求IP地址
	 */
	public static String getRequestHost() {
		HttpServletRequest request = getRequest();
		// 反向代理时获取真实ip
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 是否为Ajax请求
	 * @return
	 */
	public static boolean isAjaxRequest() {
		HttpServletRequest request = getRequest();
		return request.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
	}

}