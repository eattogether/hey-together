package com.eattogether.heytogether.domain;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int count;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem() {
    }

    public OrderItem(final String name, final int count, final Order order) {
        this.name = name;
        this.count = count;
        this.order = order;
    }
}
