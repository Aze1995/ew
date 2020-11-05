package com.ew.component.actionLog.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ew.common.utils.HttpServletUtil;
import com.ew.modules.system.entity.ActionLog;
import com.ew.modules.system.service.IActionLogService;

import lombok.extern.slf4j.Slf4j;

/**
 * 日志拦截
 * @author Mr`Huang
 * @Date 2020-11-5 18:21:08
 */
@Slf4j
@Aspect
@Component
public class ActionLogAop {

	@Autowired
	private IActionLogService actionLogService;
	
	@Pointcut("@annotation(com.ew.component.actionLog.annotation.ActionLog)")
    public void actionLog() {};
	
    @Around("actionLog()")
    public Object recordLog(ProceedingJoinPoint point) throws Throwable {
        // 先执行切入点，获取返回值
        Object proceed = point.proceed();

        /* 读取ActionLog注解消息 */
        Method targetMethod = ((MethodSignature)(point.getSignature())).getMethod();
        com.ew.component.actionLog.annotation.ActionLog anno = targetMethod.getAnnotation(com.ew.component.actionLog.annotation.ActionLog.class);
        // 获取name值
        String name = anno.name();
        // 获取message值
        String message = anno.message();
        // 获取key值
        String key = anno.key();
        
        // 保存日志信息
        ActionLog log = new ActionLog();
        log.setName(name);
        log.setClazz(point.getTarget().getClass().getName());
        log.setMethod(targetMethod.getName());
        log.setIpaddr(HttpServletUtil.getRequestHost());
        log.setMessage(message);
		actionLogService.saveAsync(log);
        return proceed;
    }
    
}
