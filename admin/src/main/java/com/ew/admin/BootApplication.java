package com.ew.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: ew
 * @author: ZeQun
 * @create: 2020-10-30 16:44
 */
@SpringBootApplication(scanBasePackages = "com.ew")
@MapperScan(basePackages = "com.ew.modules.system.mapper")
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

}