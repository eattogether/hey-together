package com.eattogether.heytogether.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

// 특정 가게의 특정 메뉴
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "value", column = @Column(name = "price")))
    private Money price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    @JsonBackReference
    private Shop shop;

    public Menu() {
    }

    public Menu(String name, Money price, Shop shop) {
        this.name = name;
        this.price = price;
        this.shop = shop;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public Shop getShop() {
        return shop;
    }
}