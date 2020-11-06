package com.ew.component.actionLog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ew.common.enums.ActionLogEnum;
import com.ew.component.actionLog.action.base.ActionSign;
import com.ew.component.actionLog.action.base.ActionSign.DefaultActionSign;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ActionLog {

    // 日志名称
    String name() default "";
    // 行为key
    String key() default "";
    //日志类型
    ActionLogEnum type() default ActionLogEnum.BUSINESS;
    // 行为类
    Class<? extends ActionSign> action() default DefaultActionSign.class;

}
