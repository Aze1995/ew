package com.ew.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 代码生成入口
 * @author Mr`Huang
 * @Date 2020-11-2 15:47:28
 */
public class App {
	

	public static void main(String[] args) {
		AutoGenerator autoGenerator =new AutoGenerator();
		autoGenerator.setDataSource(getDataSourceConfig());//数据源配置
		autoGenerator.setGlobalConfig(getGlobalConfig());//全局配置
		autoGenerator.setPackageInfo(getPackageConfig());//包名配置
		autoGenerator.setStrategy(getStrategyConfig());//生成策略配置
		autoGenerator.setTemplate(getTemplateConfig());//自定义生成模板
		autoGenerator.execute();
		
	}
	
	/**
	 * 数据源配置
	 * @return
	 */
	public static DataSourceConfig getDataSourceConfig() {
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/ew?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
		return dsc;
	}
	
	/**
	 *  包名配置
	 * @return
	 */
	public static PackageConfig getPackageConfig() {
		PackageConfig packageInfo = new PackageConfig();
		packageInfo.setParent("com.ew.modules");//包名
		packageInfo.setModuleName("system");//模块名
		return packageInfo;
	}
	
	/**
	 *  全局配置
	 * @return
	 */
	public static GlobalConfig getGlobalConfig() {
		GlobalConfig globalConfig = new GlobalConfig();
		globalConfig.setFileOverride(true);//是否覆盖已有文件
		globalConfig.setAuthor("Hr`Huang");//作者名称
		globalConfig.setOpen(true);//生成完毕是大开目录
		globalConfig.setBaseResultMap(true);//xml答应 ResultMap
		globalConfig.setSwagger2(true);//开启Swagger2
		String outputDir = "E:\\tmp\\code";
		globalConfig.setOutputDir(outputDir);//保存路径
		//--- 生成类目命名格式 ---
		globalConfig.setSwagger2(true);//开始Swaager2
		globalConfig.setEntityName("%s");
		globalConfig.setMapperName("%sMapper");
		globalConfig.setServiceName("I%sService");
		globalConfig.setServiceImplName("%sServiceImpl");
		globalConfig.setXmlName("%sMapper");
		return globalConfig;
	}
	
	/**
	 *  生成策略配置
	 * @return
	 */
	public static StrategyConfig getStrategyConfig() {
		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig.setTablePrefix("sys_");//过滤表前缀
		strategyConfig.setRestControllerStyle(true);//生成 @RestController 控制器
		strategyConfig.setEntityTableFieldAnnotationEnable(true);//是否生成实体时，生成字段注解
		strategyConfig.setNaming(NamingStrategy.underline_to_camel);//表名开启驼峰
		strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);//字段名开启驼峰
		strategyConfig.setInclude("sys_menu");//生成代码表名 支持正则
		strategyConfig.setEntityLombokModel(true);//实体为lombok模式
		
		strategyConfig.setSuperEntityClass("com.ew.common.base.BaseEntity");//实体类父类
		strategyConfig.setSuperEntityColumns("remark","status","update_by","update_date","create_by","create_date");//父类公共字段
		
		strategyConfig.setSuperServiceClass("com.ew.common.base.IBaseService");
		
		
		return strategyConfig;
	}
	
	public static TemplateConfig getTemplateConfig() {
		TemplateConfig templateConfig =new TemplateConfig();
		//https://gitee.com/baomidou/mybatis-plus/tree/3.0/mybatis-plus-generator/src/main/resources/templates
		templateConfig.setService("/templates/EwService.java.vm");
		return templateConfig;
	}

}
