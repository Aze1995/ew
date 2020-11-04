package com.ew.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ew.common.base.IBaseService;
import com.ew.common.base.controller.QueryMapping;
import com.ew.modules.system.entity.ActionLog;
import com.ew.modules.system.service.IActionLogService;

import io.swagger.annotations.Api;

@Api(tags = "日志管理")
@Validated
@RestController
@RequestMapping("/system/actionlog")
public class ActionLogController implements QueryMapping<IActionLogService, ActionLog> {

	@Autowired
	private IActionLogService actionLogService;
	
	@Override
	public IBaseService<ActionLog> getService() {
		return actionLogService;
	}

	
}
