package com.eattogether.heytogether.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private LocalDateTime deadLine;

    private Place place;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Article() {
    }

    public Article(String title, LocalDateTime deadLine, Place place) {
        this.title = title;
        this.deadLine = deadLine;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public Place getPlace() {
        return place;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Shop shop) {
        orders.add(new Order(shop, this));
    }
}
