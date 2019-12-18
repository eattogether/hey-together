package com.eattogether.heytogether.web;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.eattogether.heytogether.domain.Place;
import com.eattogether.heytogether.service.dto.ArticleCreateDto;
import com.eattogether.heytogether.service.dto.ItemCreateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ArticleAcceptanceTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("게시글 등록 성공")
    void save_article() {
        List<ItemCreateDto> items = Arrays.asList(new ItemCreateDto(1L, 1),
                new ItemCreateDto(2L, 1));
        ArticleCreateDto articleCreateDto = new ArticleCreateDto("같이묵자", LocalDateTime.now(), 1L,
                new Place(1, 1), items);

        webTestClient.post()
                .uri("/api/articles")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(articleCreateDto), ArticleCreateDto.class)
                .exchange().expectStatus().isOk();
    }

    @Test
    @DisplayName("order를 포함한 게시글 조회 성공")
    void read_article() {
        webTestClient.get()
                .uri("/api/articles/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.title").isEqualTo("같이묵자")
                .jsonPath("$.deadLine").isNotEmpty()
                .jsonPath("$.place").isNotEmpty();
    }
}
