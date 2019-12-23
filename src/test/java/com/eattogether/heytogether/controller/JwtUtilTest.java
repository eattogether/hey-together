package com.eattogether.heytogether.controller;

import com.eattogether.heytogether.common.JwtTokenException;
import com.eattogether.heytogether.common.JwtUtil;
import com.eattogether.heytogether.service.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.eattogether.TestConstant.JWT_TOKEN;
import static com.eattogether.TestConstant.USER_NAME;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtUtilTest {

    @Test
    @DisplayName("토큰 정상 생성")
    void createToken() {
        assertDoesNotThrow(() -> JwtUtil.createToken(new UserDto(USER_NAME)));
    }

    @Test
    @DisplayName("토큰 유효성 정상 검사")
    void tokenValidation() {
        assertDoesNotThrow(() -> JwtUtil.validate(JWT_TOKEN));
    }

    @Test
    @DisplayName("토큰 유효성 검사 오류")
    void tokenValidation2() {
        assertThrows(JwtTokenException.class, () -> JwtUtil.validate(JWT_TOKEN + "a"));
    }
}