package com.eattogether.heytogether.service.dto;

import com.eattogether.heytogether.domain.vo.Money;
import com.eattogether.heytogether.domain.vo.Place;

public class ShopInfoDto {

    private Long id;
    private String name;
    private Money deliveryTip;
    private Money minimumOrderPrice;
    private Place place;

    public ShopInfoDto() {
    }

    public ShopInfoDto(Long id, String name, Money deliveryTip, Money minimumOrderPrice, Place place) {
        this.id = id;
        this.name = name;
        this.deliveryTip = deliveryTip;
        this.minimumOrderPrice = minimumOrderPrice;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
