package com.ew.admin.system.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ew.admin.system.form.RoleFrom;
import com.ew.common.base.BaseAbstractController;
import com.ew.modules.system.entity.Role;
import com.ew.modules.system.service.IRoleService;

import io.swagger.annotations.Api;

@Api(tags = "角色管理")
@Validated
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseAbstractController<IRoleService, Role, RoleFrom> {

	@Override
	protected Role newInstance() {
		return new Role();
	}

}
