package com.eattogether.heytogether.service.assembler;

import com.eattogether.heytogether.domain.User;
import com.eattogether.heytogether.service.dto.UserDto;

public class UserAssembler {
    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUserId());
    }
}
