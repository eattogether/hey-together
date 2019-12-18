package com.eattogether.heytogether.service.dto;

public class OrderInfoDto {

    private Long id;
    private Long shopId;
    private Long articleId;

    public OrderInfoDto(Long id, Long shopId, Long articleId) {
        this.id = id;
        this.shopId = shopId;
        this.articleId = articleId;
    }

    public Long getId() {
        return id;
    }

    public Long getShopId() {
        return shopId;
    }

    public Long getArticleId() {
        return articleId;
    }
}
