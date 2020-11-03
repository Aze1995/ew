package com.ew.common.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 配置fastjson进行序列化
 * @author Mr`Huang
 * @Date 2020-11-3 10:31:33
 */
//@Configuration
@Deprecated
public class WebJsonConfig {

	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		// 创建FastJson信息转换对象
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

		/*
		 * QuoteFieldNames———-输出key时是否使用双引号,默认为true 
		 * WriteMapNullValue——–是否输出值为null的字段,默认为false 
		 * WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null 
		 * WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null 
		 * WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null 
		 * WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
		 */
		
		// 创建Fastjosn对象并设定序列化规则
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
	    fastJsonConfig.setSerializerFeatures(
	    		SerializerFeature.WriteNullListAsEmpty,
	    		SerializerFeature.WriteDateUseDateFormat,//处理日期时间格式化问题      
	    		SerializerFeature.WriteMapNullValue,   
	    		SerializerFeature.WriteNullStringAsEmpty,  
	    		SerializerFeature.WriteNullNumberAsZero);
	    
		// 中文乱码解决方案
		List<MediaType> mediaTypes = new ArrayList<>();
		mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);// 设定json格式且编码为UTF-8
		fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);

		// 规则赋予转换对象
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

		return new HttpMessageConverters(fastJsonHttpMessageConverter);

	}

}
