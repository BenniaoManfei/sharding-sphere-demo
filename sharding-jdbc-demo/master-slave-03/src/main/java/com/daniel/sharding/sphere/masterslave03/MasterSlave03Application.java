package com.daniel.sharding.sphere.masterslave03;

import com.daniel.sharding.sphere.masterslave03.config.MybatisPlusConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ImportAutoConfiguration(value = {io.shardingsphere.jdbc.spring.boot.SpringBootConfiguration.class})
public class MasterSlave03Application {

	public static void main(String[] args) {
		SpringApplication.run(MasterSlave03Application.class, args);
	}
}
