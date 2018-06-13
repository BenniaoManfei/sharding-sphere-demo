package com.daniel.sharding.sphere.shardingmasterslave03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ImportAutoConfiguration(value = {io.shardingsphere.jdbc.spring.boot.SpringBootConfiguration.class})
public class ShardingMasterslave03Application {

	public static void main(String[] args) {
		SpringApplication.run(ShardingMasterslave03Application.class, args);
	}
}
