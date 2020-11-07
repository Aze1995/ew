package com.ew.component.actionLog.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.ew.common.utils.HttpServletUtil;
import com.ew.common.utils.LoginUserUtil;
import com.ew.common.utils.SpringContextUtil;
import com.ew.component.actionLog.action.base.ActionSign;
import com.ew.component.actionLog.action.base.ActionSign.DefaultActionSign;
import com.ew.component.actionLog.action.base.ResultLog;
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

	HashMap<Class<? extends ActionSign>, Object> dictory = new HashMap<>();

	@Autowired
	private IActionLogService actionLogService;

	@Pointcut("@annotation(com.ew.component.actionLog.annotation.ActionLog)")
	public void actionLog() {
	};

	@Around("actionLog()")
	public Object recordLog(ProceedingJoinPoint point) throws Throwable {
		// 先执行切入点，获取返回值
		Object proceed = point.proceed();
		/* 读取ActionLog注解消息 */
		Method targetMethod = ((MethodSignature) (point.getSignature())).getMethod();
		com.ew.component.actionLog.annotation.ActionLog anno = targetMethod
				.getAnnotation(com.ew.component.actionLog.annotation.ActionLog.class);
		// 获取name值
		String name = anno.name();
		// 执行方法名
		String key = anno.key();
		// 执行类名
		Class<? extends ActionSign> action = anno.action();
		// 保存日志信息
		ActionLog actionLog = new ActionLog();
		actionLog.setName(name);
		actionLog.setClazz(point.getTarget().getClass().getName());
		actionLog.setMethod(targetMethod.getName());
		actionLog.setIpaddr(HttpServletUtil.getRequestHost());
		actionLog.setType(anno.type().getCode());//日志类型
		if (LoginUserUtil.isLogin()) {
			actionLog.setOperName(LoginUserUtil.getLoginNickName());//登入用户昵称
			actionLog.setCreateBy(LoginUserUtil.getLoginUserId());//登入用户标识			
		}
		ResultLog resultLog = new ResultLog(proceed, actionLog, point);
		if (StringUtils.isNotBlank(key) && action != DefaultActionSign.class) {
			try {
				// 重置日志消息
				Method method = action.getDeclaredMethod(key, ResultLog.class);
				method.invoke(dictory.get(action), resultLog);
			} catch (NoSuchMethodException e) {
				log.error("获取行为对象方法错误！请检查方法名称是否正确！", e);
			}
		}else {
			setDefaultMessage(resultLog);
		}
		actionLogService.saveAsync(actionLog);
		return proceed;
	}

	@PostConstruct
	public void init() {
		Map<String, ActionSign> actionSign = SpringContextUtil.getApplicationContext().getBeansOfType(ActionSign.class);
		actionSign.values().forEach(a -> {
			dictory.put(a.getClass(), a);
		});
		log.info("行为日志记录器初始化完毕");
	}
	
	/**
	 * 默认消息格式
	 * args[0]:data,args[1]:data,
	 * @param resultLog
	 */
	public void setDefaultMessage(ResultLog resultLog) {
		Object[] args = resultLog.getJoinPoint().getArgs();
		Method method = ((MethodSignature) (resultLog.getJoinPoint().getSignature())).getMethod();
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < parameterAnnotations.length; i++) {
			stringBuffer.append("args["+i+"]:");
			boolean json = false;
			for (Annotation anno : parameterAnnotations[i]) {
				if (anno instanceof RequestBody) {
					json =true;
					break;
				}
			}
			if (json && args[i] != null) {
				stringBuffer.append(JSONObject.toJSONString(args[i]));
			}else {
				stringBuffer.append(args[i]);
			}
			stringBuffer.append(",");
		}
		resultLog.getActionLog().setMessage(stringBuffer.toString());
	}
	

}
