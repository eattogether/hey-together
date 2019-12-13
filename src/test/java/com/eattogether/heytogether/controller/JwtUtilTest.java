package com.eattogether.heytogether.controller;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.eattogether.heytogether.common.JwtUtil;
import com.eattogether.heytogether.service.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtUtilTest {
    private final static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwidGltZSI6IjE1MTYyMzkwMjIifQ.LgwOkByjVf7hwCXe9iwaH1jWpjmbVn50aJCtqNlbxec";

    @Test
    @DisplayName("토큰 정상 생성")
    void createToken() {
        assertDoesNotThrow(() -> JwtUtil.createToken(new UserDto(1L, "mamook")));
    }

    @Test
    @DisplayName("토큰 유효성 정상 검사")
    void name1() {
        assertDoesNotThrow(() -> JwtUtil.validate(token));
    }

    @Test
    @DisplayName("토큰 유효성 검사 오류")
    void name() {
        assertThrows(SignatureVerificationException.class, () -> JwtUtil.validate(token + "a"));
    }
}