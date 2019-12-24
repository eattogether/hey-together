package com.eattogether.heytogether.service.dto;

import com.eattogether.heytogether.domain.ArticleStatus;
import com.eattogether.heytogether.domain.vo.Place;

import java.time.LocalDateTime;

public class ArticleInfoDto {

    private Long id;
    private String title;
    private LocalDateTime deadLine;
    private Place place;
    private ArticleStatus articleStatus;

    public ArticleInfoDto() {
    }

    public ArticleInfoDto(Long id, String title, LocalDateTime deadLine, Place place, ArticleStatus articleStatus) {
        this.id = id;
        this.title = title;
        this.deadLine = deadLine;
        this.place = place;
        this.articleStatus = articleStatus;
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

    public ArticleStatus getArticleStatus() {
        return articleStatus;
    }
}
