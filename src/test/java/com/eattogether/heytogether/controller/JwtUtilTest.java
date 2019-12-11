package com.eattogether.heytogether.controller;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.eattogether.heytogether.controller.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtUtilTest {
    private final static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJtYW1vb2siLCJ0aW1lIjoiMjAxOS0xMi0xMVQxNjozMDozMC44NDAiLCJleHAiOjE1NzYwNzY0MDB9.pFDp6Cc5Nwkva8dIEI5dEONnCt4WoxnYX7Oco2OftSg";

    @Test
    @DisplayName("토큰 정상 생성")
    void createToken() {
        assertDoesNotThrow(() -> JwtUtil.createToken(new UserDto(1L, "mamook")));
    }

    @Test
    @DisplayName("토큰 유효성 정상 검")
    void name1() {
        assertDoesNotThrow(() -> JwtUtil.validate(token));
    }

    @Test
    @DisplayName("토큰 유효성 검사 오류")
    void name() {
        assertThrows(SignatureVerificationException.class, () -> JwtUtil.validate(token + "a"));
    }
}