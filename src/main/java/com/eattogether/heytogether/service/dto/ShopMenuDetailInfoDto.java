package com.eattogether.heytogether.service.dto;

import java.util.List;

public class ShopMenuDetailInfoDto {

    private int deliveryTip;
    private int minimumOrderPrice;
    private List<ShopMenuInfoDto> menus;

    public ShopMenuDetailInfoDto() {

    }

    public ShopMenuDetailInfoDto(int deliveryTip, int minimumOrderPrice, List<ShopMenuInfoDto> menus) {
        this.deliveryTip = deliveryTip;
        this.minimumOrderPrice = minimumOrderPrice;
        this.menus = menus;
    }

    public int getDeliveryTip() {
        return deliveryTip;
    }

    public int getMinimumOrderPrice() {
        return minimumOrderPrice;
    }

    public List<ShopMenuInfoDto> getMenus() {
        return menus;
    }
}
