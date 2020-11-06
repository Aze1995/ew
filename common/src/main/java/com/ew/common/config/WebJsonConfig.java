package com.ew.common.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * Json配置
 * @author Mr`Huang
 * @Date 2020-11-3 10:31:33
 */
@Configuration
public class WebJsonConfig {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        return new HttpMessageConverters((HttpMessageConverter<?>) new JacksonHttpMessageConverter());
    }
	
	

}
