package com.eattogether.heytogether.web.config;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.eattogether.heytogether.service.config.LoginInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.Cookie;

import static com.eattogether.heytogether.common.ControllerCookieHelper.JWT_COOKIE_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class LoginInterceptorTest {

    private MockHttpServletRequest httpServletRequest;
    private MockHttpServletResponse httpServletResponse;

    @Mock
    private Object mockHandler;

    private String jwtValue = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwidGltZSI6IjE1MTYyMzkwMjIifQ.LgwOkByjVf7hwCXe9iwaH1jWpjmbVn50aJCtqNlbxec";

    @BeforeEach
    void setUp() {
        httpServletRequest = new MockHttpServletRequest();
        httpServletResponse = new MockHttpServletResponse();
    }

    @Test
    @DisplayName("토큰을 가지고 있는 경우")
    void name() throws Exception {
        httpServletRequest.setCookies(new Cookie(JWT_COOKIE_NAME, jwtValue));
        LoginInterceptor loginInterceptor = new LoginInterceptor();

        assertThat(loginInterceptor.preHandle(httpServletRequest, httpServletResponse, mockHandler)).isTrue();
    }

    @Test
    @DisplayName("쿠키가 없는 경우")
    void name2() throws Exception {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        assertThrows(NullPointerException.class, () -> loginInterceptor.preHandle(httpServletRequest, httpServletResponse, mockHandler));
    }

    @Test
    @DisplayName("토큰이 잘못된 경우")
    void name3() throws Exception {
        httpServletRequest.setCookies(new Cookie(JWT_COOKIE_NAME, jwtValue + "a"));
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        assertThrows(SignatureVerificationException.class, () -> loginInterceptor.preHandle(httpServletRequest, httpServletResponse, mockHandler));
    }
}