package com.eattogether.heytogether.service;

import com.eattogether.heytogether.controller.dto.LoginDto;
import com.eattogether.heytogether.controller.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Transactional
    public UserDto loginUser(LoginDto loginDto) {
        return new UserDto(1L, loginDto.getUserId());
    }
}
