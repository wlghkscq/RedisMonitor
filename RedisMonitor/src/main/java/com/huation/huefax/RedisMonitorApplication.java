package com.huation.huefax;
 
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.huation.SqlMapper")
@ComponentScan
@SpringBootApplication
public class RedisMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisMonitorApplication.class, args);
	}
	

}
