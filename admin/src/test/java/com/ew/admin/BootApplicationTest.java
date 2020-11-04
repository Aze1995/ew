package com.ew.admin;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ew.modules.system.entity.User;
import com.ew.modules.system.service.IDeptService;
import com.ew.modules.system.service.IMenuService;
import com.ew.modules.system.service.IRoleMenuService;
import com.ew.modules.system.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootApplicationTest {

//	@Autowired
//	private IMenuService menuService;
	@Autowired
	private IUserService userService;
//	@Autowired
//	private IDeptService deptService;
//	@Autowired
//	private IRoleMenuService roleMenuService;
	
    @Test
    public void contextLoads() {
    	User entity = new User();
    	entity.setUsername("abc");
		userService.save(entity);
    }
	
}
