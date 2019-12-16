package com.eattogether.heytogether.service.dto;

import com.eattogether.heytogether.domain.Money;
import com.eattogether.heytogether.domain.Place;

import java.util.List;

public class ShopInfoDto {

    private Long id;
    private Money deliveryTip;
    private Money minimumOrderPrice;
    private List<MenuInfoDto> menuInfos;
    private Place place;

    public ShopInfoDto() {
    }

    public ShopInfoDto(Long id, Money deliveryTip, Money minimumOrderPrice, List<MenuInfoDto> menuInfos, Place place) {
        this.id = id;
        this.deliveryTip = deliveryTip;
        this.minimumOrderPrice = minimumOrderPrice;
        this.menuInfos = menuInfos;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public Money getDeliveryTip() {
        return deliveryTip;
    }

    public Money getMinimumOrderPrice() {
        return minimumOrderPrice;
    }

    public List<MenuInfoDto> getMenuInfos() {
        return menuInfos;
    }

    public Place getPlace() {
        return place;
    }
}
