package com.eattogether.heytogether.service;

import com.eattogether.heytogether.controller.dto.LoginDto;
import com.eattogether.heytogether.controller.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("로그인 성공 테스트")
    void loginTest() {
        given(userRepository.findByUserId("mamook")).willReturn(Optional.of(new User("mamook", "1234")));
        UserService userService = new UserService(userRepository);

        UserDto userDto = userService.loginUser(new LoginDto("mamook", "1234"));

        assertThat(userDto.getUserId()).isEqualTo("mamook");
    }

    @Test
    @DisplayName("로그인 실패 테스트 아이디가 없는 경우")
    void loginTest2() {
        UserService userService = new UserService(userRepository);

        assertThrows(IllegalArgumentException.class, () -> userService.loginUser(new LoginDto("mamook", "1234")));
    }

    @Test
    @DisplayName("로그인 실패 테스트 비밀번호가 틀린 경우")
    void loginTest3() {
        given(userRepository.findByUserId("mamook")).willReturn(Optional.of(new User("mamook", "1234")));
        UserService userService = new UserService(userRepository);

        assertThrows(IllegalArgumentException.class, () -> userService.loginUser(new LoginDto("mamook", "12345")));
    }

}