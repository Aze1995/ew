package com.ew.common.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.util.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Druid 数据库连接池配置
 * 
 * @author Mr`Huang
 * @Date 2020-10-30 17:07:07
 */
@Slf4j
@Configuration
public class DruidConfig {

	@Value("${druid.login.user-name:admin}")
	private String userName;

	@Value("${druid.login.password:123456}")
	private String password;

	@Value("${druid.allow.ip:}")
	private String allowIp;

	/** 必须配置数据源，不然无法获取到sql监控，与sql防火墙监控 */
	@Bean(name = "default_databaseSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druidDataSource() {
		return new DruidDataSource();
	}

	@Bean
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ServletRegistrationBean druidServlet() {
		log.info("init Druid Servlet Configuration ");
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.addUrlMappings("/druid/*");
		Map<String, String> initParameters = new HashMap<String, String>();
		initParameters.put("loginUsername", userName);// 用户名
		initParameters.put("loginPassword", password);// 密码
		initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
		// initParameters.put("allow", allowIp); // IP白名单 (没有配置或者为空，则允许所有访问)
		// initParameters.put("deny", "");// IP黑名单 (存在共同时，deny优先于allow)
		servletRegistrationBean.setInitParameters(initParameters);
		return servletRegistrationBean;
	}

	/**
	 * 去除Druid监控页面的广告
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean removeDruidAdFilter() throws IOException {
		// 获取common.js内容
		String text = Utils.readFromResource("support/http/resources/js/common.js");
		// 屏蔽 this.buildFooter(); 直接替换为空字符串,让js没机会调用
		final String newJs = text.replace("this.buildFooter();", "");
		// 新建一个过滤器注册器对象
		FilterRegistrationBean registration = new FilterRegistrationBean();
		// 注册common.js文件的过滤器
		registration.addUrlPatterns("/druid/js/common.js");
		// 添加一个匿名的过滤器对象,并把改造过的common.js文件内容写入到浏览器
		registration.setFilter((servletRequest, servletResponse, filterChain) -> {
			// 重置缓冲区，响应头不会被重置
			servletResponse.resetBuffer();
			// 把改造过的common.js文件内容写入到浏览器
			servletResponse.getWriter().write(newJs);
		});
		return registration;
	}
}
