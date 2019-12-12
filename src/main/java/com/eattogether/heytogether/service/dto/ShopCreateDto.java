package com.eattogether.heytogether.service.dto;

import com.eattogether.heytogether.domain.Money;
import com.eattogether.heytogether.domain.Place;

public class ShopCreateDto {

    private Money deliveryTip;

    private Money minimumOrderPrice;

    private Place place;

    public ShopCreateDto() {
    }

    public ShopCreateDto(Money deliveryTip, Money minimumOrderPrice, Place place) {
        this.deliveryTip = deliveryTip;
        this.minimumOrderPrice = minimumOrderPrice;
        this.place = place;
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
