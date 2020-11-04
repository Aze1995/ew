package com.ew.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ew.common.utils.EhCacheUtil;
import com.ew.modules.system.service.IUserService;

import net.sf.ehcache.CacheManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
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
    	CacheManager cacheManager = EhCacheUtil.getCacheManager();
    }
	
}
