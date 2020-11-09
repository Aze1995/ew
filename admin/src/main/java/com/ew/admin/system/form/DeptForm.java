package com.ew.admin.system.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="部门Form", description="部门表单")
public class DeptForm {

	@NotBlank
    @ApiModelProperty(value = "部门名称")
    private String title;

	@NotNull
	@Min(value = 0)
    @ApiModelProperty(value = "父级ID")
    private Long pid;

	@NotNull
    @ApiModelProperty(value = "排序")
    private Integer sort;
	
}
