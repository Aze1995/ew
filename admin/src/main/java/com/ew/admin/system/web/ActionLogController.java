package com.ew.admin.system.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ew.modules.system.service.impl.ActionLogServiceImpl;

@RestController
@RequestMapping("/system/actionlog")
public class ActionLogController {
	
	@Autowired
	private ActionLogServiceImpl actionLogServiceImpl;

	@RequestMapping("/count")
	public Integer count() throws Exception {
		return actionLogServiceImpl.count(null);
	}
	
}
