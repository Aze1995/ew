package com.ew.common.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import com.ew.common.utils.HttpServletUtil;

/**
 * 重写Spring Boot 默认错误响应模板
 * @author Mr`Huang
 * @Date 2020-11-4 17:53:45
 */
//@Configuration
@Deprecated
public class MyErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		if (HttpServletUtil.isAjaxRequest()) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", 123456);
			map.put("message", 123456);
			return map;
		}
		return super.getErrorAttributes(webRequest, includeStackTrace);
	}
	
}
