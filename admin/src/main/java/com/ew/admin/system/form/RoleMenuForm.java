package com.ew.admin.system.form;

import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="角色菜单权限Form", description="角色菜单权限表单")
public class RoleMenuForm {

	@NotNull
	@Min(value = 1)
    @ApiModelProperty(value = "角色id")
    private Long roleId;
	
	@NotNull
	@ApiModelProperty(value = "菜单id集")
	private Set<Long> menuIds;
	
}
