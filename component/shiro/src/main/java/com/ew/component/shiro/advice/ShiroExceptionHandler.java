package com.ew.component.shiro.advice;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ew.common.dto.ResultDto;
import com.ew.common.utils.ResultDtoUtil;

/**
 * Shiro 异常拦截
 * @author Mr`Huang
 * @Date 2020-11-5 11:11:58
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ShiroExceptionHandler {
	

	/** Shiro拦截未经授权的请求-权限不足 */
	@ExceptionHandler(value = UnauthorizedException.class)
	public ResultDto<String> hadeUnauthorizedException(UnauthorizedException e) {
		return ResultDtoUtil.RequestError.badRequest("权限不足");
	}
	
}
