package com.ew.common.enums;

import lombok.Getter;

/**
 * 响应消息
 * @author Mr`Huang
 * @Date 2020年10月31日 下午3:46:59
 */
@Getter
public enum ResultEnum {

    /**
     * 通用状态
     */
    SUCCESS(0, "成功"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
	
}
