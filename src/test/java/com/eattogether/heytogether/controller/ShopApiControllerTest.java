package com.eattogether.heytogether.controller;

import com.eattogether.heytogether.domain.Place;
import com.eattogether.heytogether.service.dto.MenuCreateDto;
import com.eattogether.heytogether.service.dto.ShopCreateDto;
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
class ShopApiControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("가게 생성")
    void saveShop() {
        ShopCreateDto shopCreateDto = new ShopCreateDto(2000, 14000, new Place(1.1, 2.2));
        webTestClient.post().uri("/api/shops")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(shopCreateDto), ShopCreateDto.class)
                .exchange().expectStatus().isOk();
    }

    @Test
    @DisplayName("가게 조회")
    void readShop() {
        webTestClient.get().uri("/api/shops/2")
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(2)
                .jsonPath("$.minimumOrderPrice").isEqualTo(15000)
                .jsonPath("$.deliveryTip").isEqualTo(3000)
                .jsonPath("$.menus").isEmpty()
                .jsonPath("$.place").isNotEmpty();
    }

    @Test
    @DisplayName("메뉴 등록")
    void addMenu() {
        MenuCreateDto menuCreateDto = new MenuCreateDto("bread", 1500);

        webTestClient.post().uri("/api/shops/1/menus")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(menuCreateDto), MenuCreateDto.class)
                .exchange().expectStatus().isOk();
    }
}