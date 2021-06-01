package com.zhisen.cslcp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhisen.cslcp.dao")
public class CslcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(CslcpApplication.class, args);
	}

}
