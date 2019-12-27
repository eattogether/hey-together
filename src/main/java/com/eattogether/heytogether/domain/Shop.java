package com.eattogether.heytogether.domain;

import com.eattogether.heytogether.domain.vo.Money;
import com.eattogether.heytogether.domain.vo.Place;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "value", column = @Column(name = "deliveryTip")))
    private Money deliveryTip;

    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "value", column = @Column(name = "minimumOrderPrice")))
    private Money minimumOrderPrice;

    @Embedded
    private Place place;

    @Column(unique = true)
    private String name;

    public Shop() {
    }

    public Shop(final Money deliveryTip, final Money minimumOrderPrice, final Place place, final String name) {
        this.deliveryTip = deliveryTip;
        this.minimumOrderPrice = minimumOrderPrice;
        this.place = place;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Place getPlace() {
        return place;
    }

    public Money getDeliveryTip() {
        return deliveryTip;
    }

    public Money getMinimumOrderPrice() {
        return minimumOrderPrice;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Shop)) {
            return false;
        }
        final Shop shop = (Shop) o;
        return Objects.equals(getId(), shop.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", deliveryTip=" + deliveryTip +
                ", minimumOrderPrice=" + minimumOrderPrice +
                ", place=" + place +
                '}';
    }
}
