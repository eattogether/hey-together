package com.eattogether.heytogether.domain;

import com.eattogether.heytogether.domain.vo.Money;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Menu menu;

    private int count;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    public Item() {
    }

    public Item(Menu menu, int count, Order order) {
        this.menu = menu;
        this.count = count;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }

    public Order getOrder() {
        return order;
    }

    public Money getPrice() {
        return menu.getPrice(count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        final Item item = (Item) o;
        return Objects.equals(getId(), item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", menu=" + menu +
                ", count=" + count +
                ", order=" + order +
                '}';
    }
}
