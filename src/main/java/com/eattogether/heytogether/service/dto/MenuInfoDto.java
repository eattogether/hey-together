package com.eattogether.heytogether.service.dto;

import com.eattogether.heytogether.domain.vo.Money;

public class MenuInfoDto {

    private Long id;
    private String name;
    private Money price;
    private Long shopId;

    public MenuInfoDto() {
    }

    public MenuInfoDto(Long id, String name, Money price, Long shopId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shopId = shopId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public Long getShopId() {
        return shopId;
    }
}
