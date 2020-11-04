package com.ew.common.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import net.sf.ehcache.CacheManager;

/**
 * Ehcache 配置
 * @author Mr`Huang
 * @Date 2020-11-4 17:05:56
 */
@Configuration
public class EhcacheConfig {
	/**
	 * 缓存管理器 使用Ehcache实现
	 * 
	 * @throws IOException
	 */
	@Bean
	public CacheManager getEhCacheManager() throws IOException {
		String configFile = "classpath:ehcache.xml";
		try {
			return new net.sf.ehcache.CacheManager(ResourceUtils.getURL(configFile));
		} catch (IOException e) {
			throw new IOException("Unable to obtain input stream for cacheManagerConfigFile [" + configFile + "]", e);
		}
	}
}
