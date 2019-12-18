package com.eattogether.heytogether.service.assembler;

import java.util.List;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.service.dto.ArticleCreateDto;
import com.eattogether.heytogether.service.dto.ArticleDetailInfoDto;

public class ArticleAssembler {
    public static Article toEntity(ArticleCreateDto articleCreateDto) {
        return new Article(
                articleCreateDto.getTitle(),
                articleCreateDto.getDeadLine(),
                articleCreateDto.getPlace()
        );
    }

    public static ArticleDetailInfoDto toDto(Article article, List<Order> orders) {
        return new ArticleDetailInfoDto(
                article.getId(),
                article.getTitle(),
                article.getDeadLine(),
                article.getPlace(),
                OrderAssembler.toDto(orders)
        );
    }
}
