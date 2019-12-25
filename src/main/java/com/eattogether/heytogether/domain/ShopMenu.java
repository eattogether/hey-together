package com.eattogether.heytogether.domain;

import com.eattogether.heytogether.domain.vo.Money;

import java.util.Objects;
import javax.persistence.*;

@Entity
public class ShopMenu {

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

    public ShopMenu() {
    }

    public ShopMenu(String name, Money price, Shop shop) {
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

    public Money getPrice(int count) {
        return price.multiply(count);
    }

    public Shop getShop() {
        return shop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShopMenu)) {
            return false;
        }
        final ShopMenu shopMenu = (ShopMenu) o;
        return Objects.equals(getId(), shopMenu.getId());
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