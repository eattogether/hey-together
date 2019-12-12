package com.eattogether.heytogether.service;

import com.eattogether.heytogether.controller.dto.UserDto;

public class UserAssembler {
    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUserId());
    }
}
