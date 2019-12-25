package com.eattogether.heytogether.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {
    }

    public Order(Shop shop, Article article) {
        this.shop = shop;
        this.article = article;
    }

    public Order(Shop shop, Article article, User user) {
        this.shop = shop;
        this.article = article;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Shop getShop() {
        return shop;
    }

    public Article getArticle() {
        return article;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        final Order order = (Order) o;
        return Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", shop=" + shop +
                ", article=" + article +
                ", user=" + user +
                '}';
    }
}
