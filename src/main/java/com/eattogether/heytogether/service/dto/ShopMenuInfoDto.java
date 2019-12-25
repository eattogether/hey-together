package com.eattogether.heytogether.service.dto;

public class ShopMenuInfoDto {

    private String name;
    private int price;

    public ShopMenuInfoDto() {
    }

    public ShopMenuInfoDto(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
