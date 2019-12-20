//package com.eattogether.heytogether.web.config;
//
//import com.eattogether.heytogether.common.JwtTokenException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static com.eattogether.TestConstant.JWT_TOKEN;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@ExtendWith(SpringExtension.class)
//class LoginInterceptorTest {
//
//    private MockHttpServletRequest httpServletRequest;
//    private MockHttpServletResponse httpServletResponse;
//    private LoginInterceptor loginInterceptor;
//    @Mock
//    private Object mockHandler;
//
//    @BeforeEach
//    void setUp() {
//        httpServletRequest = new MockHttpServletRequest();
//        httpServletResponse = new MockHttpServletResponse();
//        loginInterceptor = new LoginInterceptor();
//    }
//
//    @Test
//    @DisplayName("토큰을 가지고 있는 경우")
//    void requestTokenValidation() throws Exception {
//        httpServletRequest.addHeader("Authorization", "Bearer " + JWT_TOKEN);
//
//        assertThat(loginInterceptor.preHandle(httpServletRequest, httpServletResponse, mockHandler)).isTrue();
//    }
//
//    @Test
//    @DisplayName("토큰이 없는 경우")
//    void requestTokenValidation2() throws Exception {
//        assertThrows(NullPointerException.class, () -> loginInterceptor.preHandle(httpServletRequest, httpServletResponse, mockHandler));
//    }
//
//    @Test
//    @DisplayName("토큰이 변조된 경우")
//    void requestTokenValidation3() throws Exception {
//        httpServletRequest.addHeader("Authorization", "Bearer " + JWT_TOKEN + "a");
//
//        assertThrows(JwtTokenException.class, () -> loginInterceptor.preHandle(httpServletRequest, httpServletResponse, mockHandler));
//    }
//}