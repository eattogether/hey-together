package com.eattogether.heytogether.web.config;

import com.eattogether.heytogether.common.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String HEADER_SPLITTER = " ";
    public static final String JWT_TOKEN_HEADER = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String jwtTokenHeader = request.getHeader(JWT_TOKEN_HEADER);
        if (Objects.isNull(jwtTokenHeader)) {
            response.sendRedirect("/");
            return false;
        }
        JwtUtil.validate(getJwtToken(jwtTokenHeader));

        return true;
    }

    private String getJwtToken(final String bearer) {
        return bearer.split(HEADER_SPLITTER)[1];
    }
}
