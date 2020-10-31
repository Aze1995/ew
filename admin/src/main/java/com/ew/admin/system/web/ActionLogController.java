package com.ew.admin.system.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ew.modules.system.mapper.ActionLogMapper;

@RestController
@RequestMapping("/system/actionlog")
public class ActionLogController {
	
	@Autowired
	private ActionLogMapper mapper;

	@GetMapping("/testMapper")
	public Object count() throws Exception {
		return mapper.test();
	}
	
}
