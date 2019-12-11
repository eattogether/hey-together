package com.eattogether.heytogether.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "value", column = @Column(name = "deliveryTip")))
    private Money deliveryTip;

    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "value", column = @Column(name = "minimumOrderPrice")))
    private Money minimumOrderPrice;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<Menu> menus = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
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
}
