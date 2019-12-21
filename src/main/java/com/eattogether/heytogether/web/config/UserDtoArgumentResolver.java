package com.eattogether.heytogether.web.config;

import com.eattogether.heytogether.common.JwtUtil;
import com.eattogether.heytogether.service.dto.UserDto;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import static com.eattogether.heytogether.web.config.LoginInterceptor.JWT_TOKEN_HEADER;

@Component
public class UserDtoArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String HEADER_SPLITTER = " ";

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return UserDto.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String jwtTokenHeader = request.getHeader(JWT_TOKEN_HEADER);
        String jwtToken = getJwtToken(jwtTokenHeader);
        String userId = JwtUtil.getValue(jwtToken, "iss");

        return new UserDto(userId);
    }

    private String getJwtToken(final String bearer) {
        return bearer.split(HEADER_SPLITTER)[1];
    }
}
