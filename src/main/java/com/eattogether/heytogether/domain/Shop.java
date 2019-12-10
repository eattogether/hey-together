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

    // TODO: 2019-12-09 Embeded로 바꾸기
    @Column
    private int deliveryTip;

    @Column
    private int minimumOrderPrice;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<Menu> menus = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Place place;

    public Shop() {
    }

    public Shop(int deliveryTip, int minimumOrderPrice, Place place) {
        this.deliveryTip = deliveryTip;
        this.minimumOrderPrice = minimumOrderPrice;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
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

    public List<Menu> getMenus() {
        return menus;
    }

    private void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public void addMenu(Menu menu) {
        this.menus.add(menu);
        menu.setShop(this);
    }
}
