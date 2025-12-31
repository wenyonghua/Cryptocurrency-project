package com.crypto.platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC 配置
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置视图控制器
     * 访问根路径时重定向到登录页面
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 访问 / 重定向到登录页面
        registry.addRedirectViewController("/", "/admin/login.html");
    }
}

