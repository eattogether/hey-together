package com.eattogether.heytogether;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
//    @GetMapping("/")
//    public ArticleDtoSimple test() {
//        ArticleDtoSimple articleDtoSimple = new ArticleDtoSimple("ex-title", "ex-contents");
//        return articleDtoSimple;
//    }

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    public ResponseEntity create(@RequestBody ArticleDto articleDto) {
        articleService.save(articleDto);
        return ResponseEntity.ok().build();
    }
}
