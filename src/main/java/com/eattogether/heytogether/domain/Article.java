package com.eattogether.heytogether.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private LocalDateTime deadline;

    // 주문 위치
    // TODO: 2019-12-09 전부 다 CascadeType.ALL로 괜찮은가 ?!?
    @OneToOne(cascade = CascadeType.ALL)
    private Place place;

    @OneToOne(cascade = CascadeType.ALL)
    private Shop shop;

    @OneToOne(cascade = CascadeType.ALL)
    private Orders orders;

    public Article() {
    }

    public Article(String title, LocalDateTime deadline, Place place, Shop shop, Orders orders) {
        this.title = title;
        this.deadline = deadline;
        this.place = place;
        this.shop = shop;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
