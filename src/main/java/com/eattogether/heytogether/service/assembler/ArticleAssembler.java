package com.eattogether.heytogether.service.assembler;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.User;
import com.eattogether.heytogether.service.dto.ArticleCreateDto;
import com.eattogether.heytogether.service.dto.ArticleInfoDto;

public class ArticleAssembler {

    public static Article toEntity(ArticleCreateDto articleCreateDto, User user) {
        return new Article(
                articleCreateDto.getTitle(),
                articleCreateDto.getDeadLine(),
                articleCreateDto.getPlace(),
                user
        );
    }

    public static ArticleInfoDto toDto(Article article) {
        return new ArticleInfoDto(
                article.getId(),
                article.getTitle(),
                article.getDeadLine(),
                article.getPlace(),
                article.getArticleStatus()
        );
    }
}
