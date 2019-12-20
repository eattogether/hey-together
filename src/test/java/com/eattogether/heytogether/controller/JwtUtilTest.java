package com.eattogether.heytogether.controller;

//class JwtUtilTest {
//
//    @Test
//    @DisplayName("토큰 정상 생성")
//    void createToken() {
//        assertDoesNotThrow(() -> JwtUtil.createToken(new UserDto(1L, USER_NAME)));
//    }
//
//    @Test
//    @DisplayName("토큰 유효성 정상 검사")
//    void tokenValidation() {
//        assertDoesNotThrow(() -> JwtUtil.validate(JWT_TOKEN));
//    }
//
//    @Test
//    @DisplayName("토큰 유효성 검사 오류")
//    void tokenValidation2() {
//        assertThrows(JwtTokenException.class, () -> JwtUtil.validate(JWT_TOKEN + "a"));
//    }
//}