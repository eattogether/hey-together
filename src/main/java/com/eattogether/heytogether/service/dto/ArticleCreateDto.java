package com.eattogether.heytogether.service.dto;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.Place;

public class ArticleCreateDto {
    private String title;
    private Place place;

    public ArticleCreateDto() {
    }

    public ArticleCreateDto(String title, Place place) {
        this.title = title;
        this.place = place;
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

    public Article toEntity() {
        return new Article(title, place);
    }
}
