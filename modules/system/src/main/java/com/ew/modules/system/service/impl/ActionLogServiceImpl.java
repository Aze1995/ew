package com.ew.modules.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.modules.system.entity.ActionLog;
import com.ew.modules.system.mapper.ActionLogMapper;
import com.ew.modules.system.service.IActionLogService;

/**
 * 
 * @author Mr`Huang
 * @Date 2020-10-30 18:20:26
 */
@Service
public class ActionLogServiceImpl extends ServiceImpl<ActionLogMapper, ActionLog> implements IActionLogService {

}
