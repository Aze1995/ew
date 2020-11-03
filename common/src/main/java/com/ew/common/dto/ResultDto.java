package com.ew.common.dto;

import com.ew.common.enums.ResultEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一响应
 * @author Mr`Huang
 * @Date 2020年10月31日 下午3:43:01
 * @param <T>
 */
@Data
@ApiModel("响应结果")
public class ResultDto<T> {
	
	public ResultDto(ResultEnum resultEnum, T data) {
		this.code = resultEnum.getCode();
		this.message = resultEnum.getMessage();
		this.data = data;
	}
	
	@ApiModelProperty(notes = "响应状态(成功:0)")
	private Integer code;
	@ApiModelProperty(notes = "响应描述")
	private String message;
	@ApiModelProperty(notes = "响应数据")
	private T data;
	
}
