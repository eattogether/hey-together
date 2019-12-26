package com.eattogether.heytogether.service.dto;

import com.eattogether.heytogether.domain.vo.Place;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleCreateDto {

    private String title;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
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
