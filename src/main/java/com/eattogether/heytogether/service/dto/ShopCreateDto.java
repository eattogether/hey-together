package com.eattogether.heytogether.service.dto;

import com.eattogether.heytogether.domain.vo.Money;
import com.eattogether.heytogether.domain.vo.Place;

public class ShopCreateDto {

    private Money deliveryTip;
    private Money minimumOrderPrice;
    private Place place;
    private String name;

    public ShopCreateDto() {
    }

    public ShopCreateDto(final Money deliveryTip, final Money minimumOrderPrice, final Place place, final String name) {
        this.deliveryTip = deliveryTip;
        this.minimumOrderPrice = minimumOrderPrice;
        this.place = place;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
