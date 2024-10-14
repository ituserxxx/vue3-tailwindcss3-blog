package com.example.blog.common;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有路径
                .allowedOrigins("*") // 允许的源
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的HTTP方法
                .allowedHeaders("*"); // 允许的请求头
        // .allowCredentials(true); // 是否允许带凭证
    }
}
