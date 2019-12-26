package com.eattogether.heytogether.service.dto;

public class ShopMenuInfoDto {

    private Long menuId;
    private String name;
    private int price;

    public ShopMenuInfoDto() {
    }

    public ShopMenuInfoDto(Long menuId, String name, int price) {
        this.menuId = menuId;
        this.name = name;
        this.price = price;
    }

    public Long getMenuId() {
        return menuId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
