package com.eattogether.heytogether.service.dto;

import java.util.List;

public class ArticleInfosWithShopDto {

    private List<ArticleInfoDtoWithShopId> articleInfosDtoList;

    public ArticleInfosWithShopDto(List<ArticleInfoDtoWithShopId> articleInfosDtoList) {
        this.articleInfosDtoList = articleInfosDtoList;
    }

    public List<ArticleInfoDtoWithShopId> getArticleInfosDtoList() {
        return articleInfosDtoList;
    }
}
