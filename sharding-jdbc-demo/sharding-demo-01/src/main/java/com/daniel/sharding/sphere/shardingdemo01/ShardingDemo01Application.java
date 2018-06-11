package com.daniel.sharding.sphere.shardingdemo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ImportAutoConfiguration(value = { io.shardingsphere.jdbc.spring.boot.SpringBootConfiguration.class})
public class ShardingDemo01Application {

	public static void main(String[] args) {
		SpringApplication.run(ShardingDemo01Application.class, args);
	}
}
