package com.exmple.elib.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"/css/**"}).addResourceLocations(new String[]{"classpath:/css/"});
        registry.addResourceHandler(new String[]{"/script/**"}).addResourceLocations(new String[]{"classpath:/script/"});
    }
}
