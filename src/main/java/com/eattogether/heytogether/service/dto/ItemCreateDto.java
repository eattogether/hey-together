package com.eattogether.heytogether.service.dto;

public class ItemCreateDto {
    private Long menuId;
    private int count;

    public ItemCreateDto() {
    }

    public ItemCreateDto(Long menuId, int count) {
        this.menuId = menuId;
        this.count = count;
    }

    public Long getMenuId() {
        return menuId;
    }

    public int getCount() {
        return count;
    }
}
