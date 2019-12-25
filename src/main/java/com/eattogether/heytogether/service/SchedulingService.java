package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.service.assembler.UserAssembler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchedulingService {

    private ArticleService articleService;
    private AlarmService alarmService;

    public SchedulingService(final ArticleService articleService, final AlarmService alarmService) {
        this.articleService = articleService;
        this.alarmService = alarmService;
    }

    @Scheduled(fixedRate = 1000 * 10)
    public void closeArticle() {
        LocalDateTime nowTime = LocalDateTime.now();
        List<Article> articles = articleService.findByActiveArticleEntity();

        articles.stream()
                .filter(article -> article.isEnded(nowTime))
                .map(article -> article.getAuthor())
                .map(user -> UserAssembler.toDto(user))
                .forEach(userDto -> alarmService.sendArticleEndedEmitter(userDto));
    }
}
