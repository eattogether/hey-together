package com.eattogether.heytogether.service;

import com.eattogether.heytogether.controller.dto.LoginDto;
import com.eattogether.heytogether.controller.dto.UserDto;
import com.eattogether.heytogether.dao.UserRepository;
import com.eattogether.heytogether.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("로그인 서비스")
    void login_test() {
        LoginDto loginDto = new LoginDto("aiden", "1234");

        User user = given(userRepository
                .findByUserIdAndPassword(loginDto.getUserId(), loginDto.getPassword()))
                .willReturn(Optional.of(any())).getMock();
        given(user.getId()).willReturn(1L);
        given(user.getUserId()).willReturn(loginDto.getUserId());

        UserDto userDto = userService.loginUser(loginDto);

        verify(userDto.getId()).equals(1L);
        verify(userDto.getUserId()).equals(loginDto.getUserId());
    }
}