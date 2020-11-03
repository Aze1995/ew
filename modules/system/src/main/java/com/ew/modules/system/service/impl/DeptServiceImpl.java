package com.ew.modules.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.common.Constant.DefaultConst;
import com.ew.common.utils.WrapperUtil;
import com.ew.modules.system.entity.Dept;
import com.ew.modules.system.mapper.DeptMapper;
import com.ew.modules.system.service.IDeptService;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

	@Override
	public List<Dept> findByPid(Long pid) {
		LambdaQueryWrapper<Dept> queryWrapper = WrapperUtil.lambdaQuery(new Dept());
		queryWrapper.eq(Dept::getPid, pid);
		return list(queryWrapper);
	}

	@Override
	public List<Dept> findParentDept() {
		return findByPid(DefaultConst.TOP_LEVE_PID);
	}

}
