package com.eattogether.heytogether.web.config;

import com.eattogether.heytogether.common.ControllerCookieHelper;
import com.eattogether.heytogether.common.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Cookie jwtCookie = ControllerCookieHelper.getCookie(request, ControllerCookieHelper.JWT_COOKIE_NAME);
        JwtUtil.validate(jwtCookie.getValue());

        return true;
    }
}
