package com.blog.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class druidconfig {
    @Primary
    @Bean
    public DataSource druidDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
}
