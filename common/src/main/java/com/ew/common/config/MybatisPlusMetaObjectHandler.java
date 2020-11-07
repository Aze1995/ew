package com.ew.common.config;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ew.common.utils.LoginUserUtil;

/**
 * MyBatis Plus 自动填充配置
 * @author Mr`Huang
 * @Date 2020年10月31日 下午2:10:33
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

	/*
	 * 填充原理是直接给entity的属性设置值!!!
	 * 注解则是指定该属性在对应情况下必有值,如果无值则入库会是null
	 * MetaObjectHandler提供的默认方法的策略均为:如果属性有值则不覆盖,如果填充值为null则不填充
	 * 字段必须声明TableField注解,属性fill选择对应策略,该声明告知Mybatis-Plus需要预留注入SQL字段
	 * 官方说明:https://baomidou.com/guide/auto-fill-metainfo.html
	 */
	
	@Override
	public void insertFill(MetaObject metaObject) {
		Object createBy = getFieldValByName("createBy", metaObject);
		System.err.println(Thread.currentThread().getName() +" -> MybatisPlusMetaObjectHandler.insertFill ");
		if (createBy == null) {
			setInsertFieldValByName("createBy", LoginUserUtil.getLoginUserId(), metaObject);
		}
		setInsertFieldValByName("createDate", new Date(), metaObject);
		updateFill(metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		Object updateBy = getFieldValByName("updateBy", metaObject);
		System.err.println(Thread.currentThread().getName() +" -> MybatisPlusMetaObjectHandler.updateFill ");
		if (updateBy == null) {
//			setUpdateFieldValByName("updateBy", LoginUserUtil.getLoginUserId(), metaObject);
		}
		setUpdateFieldValByName("updateDate", new Date(), metaObject);
	}

}
