package com.eattogether.heytogether.controller.dto;

import com.eattogether.heytogether.service.User;

public class UserAssembler {
    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUserId());
    }
}
