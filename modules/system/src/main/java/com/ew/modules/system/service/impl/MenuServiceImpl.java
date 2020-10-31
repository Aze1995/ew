package com.ew.modules.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.modules.system.entity.Menu;
import com.ew.modules.system.mapper.MenuMapper;
import com.ew.modules.system.service.IMenuService;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
