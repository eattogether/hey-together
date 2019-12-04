package com.eattogether.heytogether;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    @GetMapping("/")
    public ArticleDto test() {
        ArticleDto articleDto = new ArticleDto("ex-title", "ex-contents");
        return articleDto;
    }
}
