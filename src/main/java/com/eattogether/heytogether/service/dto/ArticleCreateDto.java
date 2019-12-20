package com.eattogether.heytogether.service.dto;

import com.eattogether.heytogether.domain.Place;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleCreateDto {
    private String title;

    private LocalDateTime deadLine;

    private Long shopId;

    private Place place;

    private List<ItemCreateDto> items;

    public ArticleCreateDto() {
    }

    public ArticleCreateDto(String title, LocalDateTime deadLine, Long shopId, Place place, List<ItemCreateDto> items) {
        this.title = title;
        this.deadLine = deadLine;
        this.shopId = shopId;
        this.place = place;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public Long getShopId() {
        return shopId;
    }

    public Place getPlace() {
        return place;
    }

    public List<ItemCreateDto> getItems() {
        return items;
    }
}
