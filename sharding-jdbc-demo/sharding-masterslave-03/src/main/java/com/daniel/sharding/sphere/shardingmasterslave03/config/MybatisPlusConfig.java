package com.daniel.sharding.sphere.shardingmasterslave03.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 *
 * @author DaiZM
 * @date 2018/05/07
 * @since settlement-2.0
 *
 * @history:
 * <author>          <time>          <version>          <desc>
 * DaiZM             2018/05/07           settlement-2.0           created
 *
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}