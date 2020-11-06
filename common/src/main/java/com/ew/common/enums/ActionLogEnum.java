package com.ew.common.enums;

import lombok.Getter;

/**
 * 日志类型
 * @author Mr`Huang
 * @Date 2020-11-6 14:55:03
 */
@Getter
public enum ActionLogEnum {

    /**
     * 业务日志行为
     */
    BUSINESS( 1, "业务"),
    /**
     * 用户登录日志行为
     */
    LOGIN( 2, "登录"),
    /**
     * 系统日志行为（报错信息）
     */
    SYSTEM(3, "系统");

    private Integer code;

    private String message;

    ActionLogEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}