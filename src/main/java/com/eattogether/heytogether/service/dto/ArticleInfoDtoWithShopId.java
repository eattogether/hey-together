package com.eattogether.heytogether.service.dto;

import com.eattogether.heytogether.domain.vo.Place;

import java.time.LocalDateTime;

public class ArticleInfoDtoWithShopId {
    private Long id;
    private String title;
    private LocalDateTime deadLine;
    private Place place;
    private Long shopId;
    private String userName;

    public ArticleInfoDtoWithShopId() {
    }

    public ArticleInfoDtoWithShopId(Long id, String title, LocalDateTime deadLine, Place place, Long shopId, String userName) {
        this.id = id;
        this.title = title;
        this.deadLine = deadLine;
        this.place = place;
        this.shopId = shopId;
        this.userName = userName;
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

    public Long getShopId() {
        return shopId;
    }

    public String getUserName() {
        return userName;
    }
}