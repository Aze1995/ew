package com.ew.admin;

import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ew.modules.system.service.IDeptService;
import com.ew.modules.system.service.IMenuService;
import com.ew.modules.system.service.IRoleMenuService;
import com.ew.modules.system.service.IRoleService;
import com.ew.modules.system.service.IUserService;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BootApplication.class)
public class BootApplicationTest {

	@Autowired
	private IMenuService menuService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IRoleMenuService roleMenuService;
	@Autowired
	private IRoleService roleService;
	
    @Test
    public void contextLoads() {
    	String encode = MD5Encoder.encode("123".getBytes());
    	System.out.println(encode);
    	System.out.println(encode.length());
//		System.out.println(roleService.verifyRoleId(11L));
//		System.out.println(roleService.verifyRoleId(123L));
    }
	
}
