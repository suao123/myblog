package com.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class webconfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:/Users/suao/Desktop/代码/myblog/src/main/resources/static/");
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/Users/suao/Desktop/代码/myblog/src/main/resources/upload/");
    }
}
