package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.repository.ArticleRepository;
import com.eattogether.heytogether.service.dto.ArticleCreateDto;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void save(ArticleCreateDto articleCreateDto) {
        articleRepository.save(articleCreateDto.toEntity());
    }
}
