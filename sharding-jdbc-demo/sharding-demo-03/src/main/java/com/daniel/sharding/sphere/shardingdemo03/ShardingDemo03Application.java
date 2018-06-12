package com.daniel.sharding.sphere.shardingdemo03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ImportAutoConfiguration(value = {io.shardingsphere.jdbc.spring.boot.SpringBootConfiguration.class})
public class ShardingDemo03Application {

	public static void main(String[] args) {
		SpringApplication.run(ShardingDemo03Application.class, args);
	}
}
