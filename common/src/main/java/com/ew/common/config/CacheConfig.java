package com.ew.common.config;

import java.io.IOException;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import net.sf.ehcache.CacheManager;

///**
// * 缓存配置
// * @author Mr`Huang
// * @Date 2020-11-4 17:05:56
// */
//@EnableCaching
//@Configuration
//public class CacheConfig {
//	
//	/**
//	 * 开启Spring Boot缓存
//	 * @return
//	 */
//    @Bean
//    public ConcurrentMapCacheManager cacheManager() {
//        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
////        cacheManager.setStoreByValue(true); //true表示缓存一份副本，否则缓存引用
//        return cacheManager;
//    }
//	
//	/**
//	 * 配置使用Ehcache实现
//	 * @throws IOException
//	 */
//	@Bean
//	public CacheManager getEhCacheManager() throws IOException {
//		String configFile = "classpath:ehcache.xml";
//		try {
//			return new net.sf.ehcache.CacheManager(ResourceUtils.getURL(configFile));
//		} catch (IOException e) {
//			throw new IOException("Unable to obtain input stream for cacheManagerConfigFile [" + configFile + "]", e);
//		}
//	}
//}
