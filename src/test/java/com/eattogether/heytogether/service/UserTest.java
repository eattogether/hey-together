package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.eattogether.TestConstant.USER_NAME;
import static com.eattogether.TestConstant.USER_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test
    @DisplayName("유저 비밀번호 검증 성공 테스트")
    void checkPassword() {
        User user = new User(USER_NAME, USER_PASSWORD);
        assertThat(user.checkPassword("1234")).isTrue();
    }

    @Test
    @DisplayName("유저 비밀번호가 널일때 검증 실패 테스트")
    void checkPassword2() {
        User user = new User(USER_NAME, USER_PASSWORD);
        assertThat(user.checkPassword(null)).isFalse();
    }

    @Test
    @DisplayName("유저 비밀번호가 다를때 검증 실패 테스트")
    void checkPassword3() {
        User user = new User(USER_NAME, USER_PASSWORD);
        assertThat(user.checkPassword("234")).isFalse();
    }

    @Test
    @DisplayName("유저 마일리지가 부족할 때 에러발생 테스")
    void participateTest() {
        User user = new User(USER_NAME, USER_PASSWORD);
        assertThrows(IllegalArgumentException.class, () -> user.participate(10_000));
    }
}