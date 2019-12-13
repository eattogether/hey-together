package com.eattogether.heytogether.controller;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.eattogether.heytogether.common.JwtUtil;
import com.eattogether.heytogether.service.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtUtilTest {
    private final static String JWT_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHBpcmUiOiIyMTE5LTEyLTEzVDE5OjA0OjE4LjUwMSIsImlzcyI6Im1hbW9vayIsInRpbWUiOiIyMDE5LTEyLTEzVDE5OjA0OjE4LjUwMSJ9.H685WnceIGgSx4WjHlv6V_i1p63ORLaU0Yt8R7rg_Uc";

    @Test
    @DisplayName("토큰 정상 생성")
    void createToken() {
        assertDoesNotThrow(() -> JwtUtil.createToken(new UserDto(1L, "mamook")));
    }

    @Test
    @DisplayName("토큰 유효성 정상 검사")
    void tokenValidation() {
        assertDoesNotThrow(() -> JwtUtil.validate(JWT_TOKEN));
    }

    @Test
    @DisplayName("토큰 유효성 검사 오류")
    void tokenValidation2() {
        assertThrows(SignatureVerificationException.class, () -> JwtUtil.validate(JWT_TOKEN + "a"));
    }
}