package com.eattogether.heytogether.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    //TODO : User 객체 추가

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private Article article;

    public Order() {
    }

    public Order(Shop shop, Article article) {
        this.shop = shop;
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public Shop getShop() {
        return shop;
    }

    public List<Item> getItems() {
        return items;
    }

    public Article getArticle() {
        return article;
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
