package com.eattogether.heytogether.service.dto;

import java.util.Map;

public class ArticleParticipateDto {

    private Map<String, Integer> items;
    private String shopName;

    public Map<String, Integer> getItems() {
        return items;
    }

    public String getShopName() {
        return shopName;
    }

    public String getMenuName() {
        return null;
    }

    public int getTotalAmount() {
        return 0;
    }
}
