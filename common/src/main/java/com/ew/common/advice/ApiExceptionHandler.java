package com.ew.common.advice;


import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ew.common.dto.ResultDto;
import com.ew.common.utils.ResultDtoUtil;

import lombok.extern.slf4j.Slf4j;

/**
 *  全局接口异常处理类
 * @author Mr`Huang
 * @Date 2020-11-2 17:48:13
 */
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

	/** 表单参数Form提交时, valid校验不通过时通用拦截 */
	@ExceptionHandler(value = BindException.class)
	public ResultDto<String> hadeBindException(BindException exception) {
		FieldError fieldError = exception.getFieldError();
		return ResultDtoUtil.RequestError.parameter(fieldError.getField()+"-"+fieldError.getDefaultMessage());
	}
	
	/** RequestBody参数提交时, valid校验不通过时通用拦截 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResultDto<String> hadeMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		FieldError fieldError = exception.getBindingResult().getFieldError();
		return ResultDtoUtil.RequestError.parameter(fieldError.getField()+"-"+fieldError.getDefaultMessage()); 
	}
	 
	/** 方法单个参数提交, valid校验不通过时拦截 */
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResultDto<String> hadeConstraintViolationException(ConstraintViolationException exception) {
		ConstraintViolation<?> next = exception.getConstraintViolations().iterator().next();
		return ResultDtoUtil.RequestError.parameter(next.getPropertyPath()+"-"+next.getMessage()); 
	}
	
	/** 其他错误 */
	@ExceptionHandler({ Exception.class })
	public ResultDto<String> exception(Exception ex, HttpServletRequest req) {
		log.error("未定义系统异常 - 请求接口[{}]  ",req.getRequestURL().toString(), ex);
		return ResultDtoUtil.SystemError.undefined();
	} 
}
