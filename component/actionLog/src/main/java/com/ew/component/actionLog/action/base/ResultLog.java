package com.ew.component.actionLog.action.base;

import org.aspectj.lang.JoinPoint;

import com.ew.common.dto.ResultDto;
import com.ew.common.enums.ResultEnum;
import com.ew.modules.system.entity.ActionLog;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义日志数据
 * @author Mr`Huang
 * @Date 2020-11-6 14:03:29
 */
@Data
@NoArgsConstructor
public class ResultLog {

    /* 封装操作对象 */
    /** 注解日志的方法返回值 */
    private Object retValue;
    /** 获取日志实体对象 */
    private ActionLog actionLog;
    /** Aop连接点信息对象 */
    private JoinPoint joinPoint;
    
    
    
    /**
     * 执行成功
     * @return
     */
    public boolean isSuccess() {
    	return retValue instanceof ResultDto && ResultEnum.SUCCESS.getCode().equals(((ResultDto<?>)retValue).getCode());
    }


	public ResultLog(Object retValue, ActionLog actionLog, JoinPoint joinPoint) {
		super();
		this.retValue = retValue;
		this.actionLog = actionLog;
		this.joinPoint = joinPoint;
	}
	
}
