package com.eattogether.heytogether.service.dto;

import java.time.LocalDateTime;

import com.eattogether.heytogether.domain.Place;

public class ArticleInfoDto {

    private Long id;
    private String title;
    private LocalDateTime deadLine;
    private Place place;

    public ArticleInfoDto(Long id, String title, LocalDateTime deadLine, Place place) {
        this.id = id;
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
}
