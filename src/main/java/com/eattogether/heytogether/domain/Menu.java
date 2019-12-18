package com.eattogether.heytogether.domain;

import java.util.Objects;
import javax.persistence.*;

// 특정 가게의 특정 메뉴
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "menu_name")
    private String name;

    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "value", column = @Column(name = "price")))
    private Money price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Menu)) {
            return false;
        }
        final Menu menu = (Menu) o;
        return Objects.equals(getId(), menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", shop=" + shop +
                '}';
    }
}