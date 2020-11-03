package com.ew.admin.system.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ew.admin.system.form.DeptForm;
import com.ew.common.base.BaseAbstractController;
import com.ew.modules.system.entity.Dept;
import com.ew.modules.system.service.IDeptService;

import io.swagger.annotations.Api;

@Api(tags = "部门管理")
@Validated
@RestController
@RequestMapping("/system/dept")
public class DeptController extends BaseAbstractController<IDeptService,Dept, DeptForm> {

	@Override
	public Dept newInstance() {
		return new Dept();
	}

	
	
}
