package com.eattogether.heytogether.service.dto;

import com.eattogether.heytogether.domain.Menu;
import com.eattogether.heytogether.domain.Shop;

public class MenuCreateDto {
    private String name;
    private int price;

    public MenuCreateDto() {
    }

    public MenuCreateDto(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Menu toEntity(Shop shop) {
        return new Menu(name, price, shop);
    }
}
