package com.eattogether.heytogether.web.controller;

import com.eattogether.heytogether.service.dto.ArticleCreateDto;
import com.eattogether.heytogether.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleApiController {
    private ArticleService articleService;

    public ArticleApiController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    public ResponseEntity create(@RequestBody ArticleCreateDto articleCreateDto) {
        articleService.save(articleCreateDto);
        return ResponseEntity.ok().build();
    }
}
