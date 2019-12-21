package com.eattogether.heytogether.web.config;

import com.eattogether.heytogether.service.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.core.MethodParameter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

import static com.eattogether.TestConstant.*;
import static com.eattogether.heytogether.web.config.LoginInterceptor.JWT_TOKEN_HEADER;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class UserDtoArgumentResolverTest {

    @Mock
    ModelAndViewContainer modelAndViewContainer;

    @Mock
    NativeWebRequest webRequest;

    @Mock
    WebDataBinderFactory webDataBinderFactory;

    @Mock
    HttpServletRequest httpServletRequest;

    @Test
    @DisplayName("파라미터가 UserDto인 경우 테스트")
    void resolverTest() throws NoSuchMethodException {
        UserDtoArgumentResolver userDtoArgumentResolver = new UserDtoArgumentResolver();
        MethodParameter methodParameter = getMethodParameter();
        assertThat(userDtoArgumentResolver.supportsParameter(methodParameter)).isTrue();
    }

    @Test
    @DisplayName("파라미터가 UserDto인 경우 테스트")
    void resolverTest2() throws Exception {
        UserDtoArgumentResolver userDtoArgumentResolver = new UserDtoArgumentResolver();
        MethodParameter methodParameter = getMethodParameter();
        given(webRequest.getNativeRequest()).willReturn(httpServletRequest);
        given(httpServletRequest.getHeader(JWT_TOKEN_HEADER)).willReturn(JWT_HTTP + JWT_TOKEN);

        UserDto userDto = (UserDto) userDtoArgumentResolver.resolveArgument(methodParameter, modelAndViewContainer, webRequest, webDataBinderFactory);
        assertThat(userDto.getUserId()).isEqualTo(USER_NAME);
    }


    private MethodParameter getMethodParameter() throws NoSuchMethodException {
        Method resolverTest = getTestMethod();
        return new MethodParameter(resolverTest, 0);
    }

    private Method getTestMethod() throws NoSuchMethodException {
        return UserDtoArgumentResolverTest.class.getMethod("resolverTest", UserDto.class);
    }

    public String resolverTest(UserDto userDto) {
        return "hello World";
    }
}