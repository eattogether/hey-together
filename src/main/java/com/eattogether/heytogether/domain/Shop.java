package com.eattogether.heytogether.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    @Embedded
    private Place place;

    public Shop() {
    }

    public Shop(Money deliveryTip, Money minimumOrderPrice, Place place) {
        this.deliveryTip = deliveryTip;
        this.minimumOrderPrice = minimumOrderPrice;
        this.place = place;
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

    public List<Menu> getMenus() {
        return menus;
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
                ", menus=" + menus +
                ", place=" + place +
                '}';
    }
}
