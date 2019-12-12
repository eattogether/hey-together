package com.eattogether.heytogether.web.controller;

import com.eattogether.heytogether.domain.Money;
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
class ShopApiAcceptanceTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("가게 등록 성공")
    void saveShop() {
        ShopCreateDto shopCreateDto = new ShopCreateDto(new Money(2000), new Money(14000),
                new Place(1.1, 2.2));
        webTestClient.post().uri("/api/shops")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(shopCreateDto), ShopCreateDto.class)
                .exchange().expectStatus().isOk();
    }

    @Test
    @DisplayName("가게 조회 요청 시 해당 가게의 정보 반환")
    void readShop() {
        webTestClient.get().uri("/api/shops/2")
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(2)
                .jsonPath("$.deliveryTip").isNotEmpty()
                .jsonPath("$.minimumOrderPrice").isNotEmpty()
                .jsonPath("$.menuInfos").isEmpty()
                .jsonPath("$.place").isNotEmpty();
    }

    @Test
    @DisplayName("메뉴 등록 성공")
    void addMenu() {
        MenuCreateDto menuCreateDto = new MenuCreateDto("bread", new Money(1500));

        webTestClient.post().uri("/api/shops/1/menus")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(menuCreateDto), MenuCreateDto.class)
                .exchange().expectStatus().isOk();
    }
}