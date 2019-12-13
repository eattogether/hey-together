package com.eattogether.heytogether.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Component
public class WebConfig implements WebMvcConfigurer {

    private static final List<String> LOGIN_PATTERNS = Arrays.asList("/logout");
    private static final List<String> GUEST_PATTERNS = Arrays.asList("/login");

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns(LOGIN_PATTERNS)
                .excludePathPatterns(GUEST_PATTERNS)
        ;
    }
}
