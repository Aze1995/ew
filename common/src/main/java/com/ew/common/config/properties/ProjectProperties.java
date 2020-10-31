package com.ew.common.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 系统配置
 * @author Mr`Huang
 * @Date 2020年10月31日 上午10:20:23
 */
@Data
@Component
@ConfigurationProperties(prefix = "project")
public class ProjectProperties {

    /** 是否开启Swagger数据接口文档 */
    private boolean swaggerEnabled = true;
    /** druid 权限配置 */
    private Druid druid = new Druid();
    
    @Data
    public static class Druid{
    	/**登入账户*/
    	private String userName = "ew";
    	/**登入密码*/
    	private String password = "1234561";
    	/**IP白名单*/
    	private String allowIp = "";
    	/**IP黑名单*/
    	private String denyIp = "";
    }
	
}
