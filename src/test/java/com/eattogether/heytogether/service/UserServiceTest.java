package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.repository.UserRepository;
import com.eattogether.heytogether.service.dto.LoginDto;
import com.eattogether.heytogether.service.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.eattogether.common.Constant.USER_NAME;
import static com.eattogether.common.Constant.USER_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private User user;
    private LoginDto loginDto;

    @BeforeEach
    void setUp() {
        user = new User(USER_NAME, USER_PASSWORD);
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    void loginTest() {
        given(userRepository.findByUserId(USER_NAME)).willReturn(Optional.of(user));
        loginDto = new LoginDto(USER_NAME, USER_PASSWORD);

        UserService userService = new UserService(userRepository);
        UserDto userDto = userService.loginUser(loginDto);

        assertThat(userDto.getUserId()).isEqualTo(USER_NAME);
    }

    @Test
    @DisplayName("로그인 실패 테스트 아이디가 없는 경우")
    void loginTest2() {
        UserService userService = new UserService(userRepository);
        loginDto = new LoginDto(USER_NAME + "A", USER_PASSWORD);
        assertThrows(IllegalArgumentException.class, () -> userService.loginUser(loginDto));
    }

    @Test
    @DisplayName("로그인 실패 테스트 비밀번호가 틀린 경우")
    void loginTest3() {
        given(userRepository.findByUserId(USER_NAME)).willReturn(Optional.of(user));
        UserService userService = new UserService(userRepository);

        assertThrows(IllegalArgumentException.class, () -> userService.loginUser(new LoginDto(USER_NAME, "12345")));
    }

}