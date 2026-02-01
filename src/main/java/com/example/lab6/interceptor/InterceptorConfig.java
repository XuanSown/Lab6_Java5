package com.example.lab6.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
//Interceptor chính
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    GlobalInterceptor globalInterceptor;

    @Autowired
    AuthInterceptor authInterceptor;

    @Autowired
    LoginInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 1. Global Interceptor: Chạy cho tất cả các request để hiện Menu
        registry.addInterceptor(globalInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**", "/assets/**");

        // 2. Auth Interceptor: Chỉ bảo vệ các đường dẫn cần đăng nhập
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/order/**", "/admin/**", "/account/change-password", "/account/profile")
                .excludePathPatterns("/assets/**", "/admin/home/index");

        // 3. Log Interceptor: Ghi log mọi thứ (hoặc tùy chọn)
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/assets/**");
    }
}
