package com.eattogether.heytogether.web.controller;

import com.eattogether.heytogether.service.ArticleService;
import com.eattogether.heytogether.service.dto.ArticleCreateDto;
import com.eattogether.heytogether.service.dto.ArticleInfoDto;
import com.eattogether.heytogether.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/api/articles")
    public ResponseEntity saveArticle(@RequestBody ArticleCreateDto articleCreateDto) {
        articleService.saveArticle(articleCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleInfoDto> readArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.findById(id));
    }

    @PostMapping("/api/articles/{id}")
    public ResponseEntity participate(@PathVariable Long id, @RequestBody UserDto userDto) {
        articleService.participate(id, userDto);
        return ResponseEntity.ok().build();
    }
}
