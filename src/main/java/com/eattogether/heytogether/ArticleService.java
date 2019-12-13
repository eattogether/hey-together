package com.eattogether.heytogether;

import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article save(ArticleDto articleDto) {
        Article article = ArticleAssembler.toEntity(articleDto);
        return articleRepository.save(article);
    }
}
