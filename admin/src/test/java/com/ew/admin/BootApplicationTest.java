package com.ew.admin;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ew.modules.system.entity.Menu;
import com.ew.modules.system.mapper.MenuMapper;
import com.ew.modules.system.service.IMenuService;
import com.ew.modules.system.vo.MenuVo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootApplicationTest {

	@Autowired
	private IMenuService menuService;
	
    @Test
    public void contextLoads() {
    	List<MenuVo> list = menuService.findAll();
		for (MenuVo menuVo : list) {
			System.err.println(menuVo);
		}
    }
	
}
