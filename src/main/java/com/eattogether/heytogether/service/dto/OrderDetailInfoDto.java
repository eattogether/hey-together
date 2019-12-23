package com.eattogether.heytogether.service.dto;

import java.util.Objects;

public class OrderDetailInfoDto {

    private int deliveryTip;
    private int minimumOrderPrice;
    private int totalPrice;

    public OrderDetailInfoDto() {
    }

    public OrderDetailInfoDto(int deliveryTip, int minimumOrderPrice, int totalPrice) {
        this.deliveryTip = deliveryTip;
        this.minimumOrderPrice = minimumOrderPrice;
        this.totalPrice = totalPrice;
    }

    public int getDeliveryTip() {
        return deliveryTip;
    }

    public int getMinimumOrderPrice() {
        return minimumOrderPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailInfoDto that = (OrderDetailInfoDto) o;
        return deliveryTip == that.deliveryTip &&
                minimumOrderPrice == that.minimumOrderPrice &&
                totalPrice == that.totalPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryTip, minimumOrderPrice, totalPrice);
    }

    @Override
    public String toString() {
        return "OrderDetailInfoDto{" +
                "deliveryTip=" + deliveryTip +
                ", minimumOrderPrice=" + minimumOrderPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
