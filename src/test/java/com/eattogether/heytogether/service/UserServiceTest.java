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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    private final static String userName = "mamook";
    private final static String userPassword = "1234";
    @Mock
    private UserRepository userRepository;

    private User user;
    private LoginDto loginDto;

    @BeforeEach
    void setUp() {
        user = new User(userName, userPassword);
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    void loginTest() {
        given(userRepository.findByUserId(userName)).willReturn(Optional.of(user));
        UserService userService = new UserService(userRepository);

        UserDto userDto = userService.loginUser(loginDto);

        assertThat(userDto.getUserId()).isEqualTo(userName);
    }

    @Test
    @DisplayName("로그인 실패 테스트 아이디가 없는 경우")
    void loginTest2() {
        UserService userService = new UserService(userRepository);

        assertThrows(IllegalArgumentException.class, () -> userService.loginUser(loginDto));
    }

    @Test
    @DisplayName("로그인 실패 테스트 비밀번호가 틀린 경우")
    void loginTest3() {
        given(userRepository.findByUserId(userName)).willReturn(Optional.of(user));
        UserService userService = new UserService(userRepository);

        assertThrows(IllegalArgumentException.class, () -> userService.loginUser(new LoginDto(userName, "12345")));
    }

}