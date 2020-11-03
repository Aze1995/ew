package com.ew.admin.system.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "日志管理")
@Validated
@RestController
@RequestMapping("/system/actionlog")
public class ActionLogController {

	
}
