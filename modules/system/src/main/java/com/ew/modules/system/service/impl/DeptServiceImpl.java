package com.ew.modules.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.modules.system.entity.Dept;
import com.ew.modules.system.mapper.DeptMapper;
import com.ew.modules.system.service.IDeptService;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}
