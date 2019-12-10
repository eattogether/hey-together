package com.eattogether.heytogether.service.dto;

import com.eattogether.heytogether.domain.Place;
import com.eattogether.heytogether.domain.Shop;

public class ShopCreateDto {
    private int deliveryTip;

    private int minimumOrderPrice;

    private Place place;

    public ShopCreateDto() {
    }

    public ShopCreateDto(int deliveryTip, int minimumOrderPrice, Place place) {
        this.deliveryTip = deliveryTip;
        this.minimumOrderPrice = minimumOrderPrice;
        this.place = place;
    }

    public int getDeliveryTip() {
        return deliveryTip;
    }

    public void setDeliveryTip(int deliveryTip) {
        this.deliveryTip = deliveryTip;
    }

    public int getMinimumOrderPrice() {
        return minimumOrderPrice;
    }

    public void setMinimumOrderPrice(int minimumOrderPrice) {
        this.minimumOrderPrice = minimumOrderPrice;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Shop toEntity() {
        return new Shop(deliveryTip, minimumOrderPrice, place);
    }
}
