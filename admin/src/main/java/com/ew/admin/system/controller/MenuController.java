package com.ew.admin.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ew.common.dto.ResultDto;
import com.ew.common.utils.ResultDtoUtil;
import com.ew.modules.system.service.IMenuService;
import com.ew.modules.system.vo.MenuVo;

import io.swagger.annotations.Api;

/**
 * 菜单
 * @author Mr`Huang
 * @Date 2020年10月31日 下午7:47:17
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/system/menu")
public class MenuController {

	@Autowired
	private IMenuService menuService;
	
	@GetMapping("list")
	public ResultDto<List<MenuVo>> list(){
		return ResultDtoUtil.success(menuService.findAll());
	}
	
}
