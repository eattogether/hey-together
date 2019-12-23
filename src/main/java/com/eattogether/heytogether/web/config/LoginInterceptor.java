package com.eattogether.heytogether.web.config;

import com.eattogether.heytogether.common.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String jwtToken = JwtUtil.getJwtTokenFrom(request);
        JwtUtil.validate(jwtToken);

        return true;
    }
}
