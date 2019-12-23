package com.eattogether.heytogether.domain;

import com.eattogether.heytogether.domain.vo.Money;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private ShopMenu shopMenu;

    private int count;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem() {
    }

    public OrderItem(ShopMenu shopMenu, int count, Order order) {
        this.shopMenu = shopMenu;
        this.count = count;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public ShopMenu getShopMenu() {
        return shopMenu;
    }

    public int getCount() {
        return count;
    }

    public Order getOrder() {
        return order;
    }

    public Money getPrice() {
        return shopMenu.getPrice(count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderItem)) {
            return false;
        }
        final OrderItem orderItem = (OrderItem) o;
        return Objects.equals(getId(), orderItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", menu=" + shopMenu +
                ", count=" + count +
                ", order=" + order +
                '}';
    }
}
