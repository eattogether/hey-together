package com.eattogether.heytogether.service.dto;

public class UserDto {

    private String userId;

    public UserDto() {
    }

    public UserDto(final String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
