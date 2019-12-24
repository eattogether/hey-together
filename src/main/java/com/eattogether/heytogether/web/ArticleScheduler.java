package com.eattogether.heytogether.web;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleScheduler {

    @Autowired
    ArticleService articleService;

    @Scheduled(fixedRate = 1000 * 60)
    public void closeArticle() {
        LocalDateTime nowTime = LocalDateTime.now();
        List<Article> articles = articleService.findByActiveArticle2();
        List<Long> collect = articles.stream()
                .filter(article -> article.isEnded(nowTime))
                .map(article -> article.getId())
                .collect(Collectors.toList());


    }
}
