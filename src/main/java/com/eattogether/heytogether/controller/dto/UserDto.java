package com.eattogether.heytogether.controller.dto;

public class UserDto {

    private Long id;
    private String userId;

    public UserDto() {
    }

    public UserDto(Long id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }
}
