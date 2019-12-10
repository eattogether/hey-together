package com.eattogether.heytogether.service.dto;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.Orders;
import com.eattogether.heytogether.domain.Place;
import com.eattogether.heytogether.domain.Shop;

import java.time.LocalDateTime;

public class ArticleCreateDto {
    private String title;
    private LocalDateTime deadline;
    private Place place;
    private Shop shop;
    private Orders orders;

    public ArticleCreateDto() {
    }

    public ArticleCreateDto(String title, LocalDateTime deadline, Place place, Shop shop, Orders orders) {
        this.title = title;
        this.deadline = deadline;
        this.place = place;
        this.shop = shop;
        this.orders = orders;
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

    public Article toEntity() {
        return new Article(title, deadline, place, shop, orders);
    }
}
