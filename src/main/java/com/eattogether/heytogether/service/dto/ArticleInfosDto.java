package com.eattogether.heytogether.service.dto;

import java.util.List;

public class ArticleInfosDto {

    List<ArticleInfoDto> articleInfosDtoList;

    public ArticleInfosDto() {
    }

    public ArticleInfosDto(List<ArticleInfoDto> articleInfosDtoList) {
        this.articleInfosDtoList = articleInfosDtoList;
    }

    public List<ArticleInfoDto> getArticleInfosDtoList() {
        return articleInfosDtoList;
    }
}
