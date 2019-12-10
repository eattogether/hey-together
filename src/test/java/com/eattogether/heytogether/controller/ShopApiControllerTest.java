package com.eattogether.heytogether.controller;

import com.eattogether.heytogether.domain.Place;
import com.eattogether.heytogether.service.dto.ShopCreateDto;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ShopApiControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void save() {
        ShopCreateDto shopCreateDto = new ShopCreateDto(2000, 14000, new Place(1.1, 2.2));
        webTestClient.post().uri("/api/shops")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(shopCreateDto), ShopCreateDto.class)
                .exchange().expectStatus().isOk();
    }
}