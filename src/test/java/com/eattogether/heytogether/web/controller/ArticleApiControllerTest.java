package com.eattogether.heytogether.web.controller;

import com.eattogether.heytogether.domain.Place;
import com.eattogether.heytogether.service.dto.ArticleCreateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ArticleApiControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("게시글 등록")
    void create_article() {
        ArticleCreateDto articleCreateDto = new ArticleCreateDto("test", new Place(1.1, 2.2));

        webTestClient.post().uri("/articles")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(articleCreateDto), ArticleCreateDto.class)
                .exchange().expectStatus().isOk();
    }
}