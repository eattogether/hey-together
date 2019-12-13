package com.eattogether.heytogether;

public class ArticleAssembler {
    public static Article toEntity(ArticleDto articleDto) {
        return new Article(articleDto.getName());
    }
}