package com.eattogether.heytogether.service.dto;

import java.util.List;

public class ArticleParticipateDto {
    private Long shopId;
    private List<ItemCreateDto> items;
    private int totalPrice;

    public ArticleParticipateDto(Long shopId, List<ItemCreateDto> items, int totalPrice) {
        this.shopId = shopId;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public List<ItemCreateDto> getItems() {
        return items;
    }

    public Long getShopId() {
        return shopId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}

