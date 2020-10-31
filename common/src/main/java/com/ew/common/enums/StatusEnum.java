package com.ew.common.enums;

import com.ew.common.Constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author Mr`Huang
 * @Date 2020年10月31日 下午12:39:06
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    /**
     * 正常的数据
     */
    OK(Constant.StatusConst.OK, "正常"),
    /**
     * 数据已被删除
     */
    DELETE(Constant.StatusConst.DELETE, "删除");

    private Integer code;

    private String message;
}