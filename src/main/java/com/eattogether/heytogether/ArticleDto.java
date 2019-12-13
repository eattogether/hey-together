package com.eattogether.heytogether;

public class ArticleDto {
    private String name;

    public ArticleDto() {
    }

    public ArticleDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}