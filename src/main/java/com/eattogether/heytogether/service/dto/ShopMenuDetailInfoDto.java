package com.eattogether.heytogether.service.dto;

import java.util.List;

public class ShopMenuDetailInfoDto {

    private Long shopId;
    private String shopName;
    private int deliveryTip;
    private int minimumOrderPrice;
    private List<ShopMenuInfoDto> menus;

    public ShopMenuDetailInfoDto() {
    }

    public ShopMenuDetailInfoDto(Long shopId, String shopName, int deliveryTip, int minimumOrderPrice, List<ShopMenuInfoDto> menus) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.deliveryTip = deliveryTip;
        this.minimumOrderPrice = minimumOrderPrice;
        this.menus = menus;
    }

    public Long getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
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
