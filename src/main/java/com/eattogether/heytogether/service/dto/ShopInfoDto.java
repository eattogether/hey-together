package com.eattogether.heytogether.service.dto;

import com.eattogether.heytogether.domain.Money;
import com.eattogether.heytogether.domain.Place;

public class ShopInfoDto {

    private Long id;
    private Money deliveryTip;
    private Money minimumOrderPrice;
    private Place place;

    public ShopInfoDto() {
    }

    public ShopInfoDto(Long id, Money deliveryTip, Money minimumOrderPrice, Place place) {
        this.id = id;
        this.deliveryTip = deliveryTip;
        this.minimumOrderPrice = minimumOrderPrice;
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

    public Place getPlace() {
        return place;
    }
}
