package com.eattogether.heytogether.web.controller;

import com.eattogether.heytogether.domain.*;
import com.eattogether.heytogether.service.dto.ArticleCreateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ArticleApiControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("게시글 등록")
    void create_article() {
        Place place = new Place(1.1, 2.2);
        Shop shop1 = new Shop();

        Menu pizza = new Menu("pizza", 100, shop1);
        Menu cheese = new Menu("cheese", 200, shop1);

        shop1.setDeliveryTip(3333);
        shop1.setMinimumOrderPrice(1111);
        shop1.setPlace(place);
        shop1.addMenu(pizza);
        shop1.addMenu(cheese);

        //Shop shop = new Shop(3000, 10000, new Place(3.3, 4.4));

        Orders orders = new Orders();
        orders.setItems(Arrays.asList(
                new Item(orders, pizza, 1),
                new Item(orders, cheese, 2)));

        ArticleCreateDto articleCreateDto = new ArticleCreateDto(
                "test order",
                LocalDateTime.now(),
                place,
                shop1,
                orders);

        webTestClient.post().uri("/articles")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(articleCreateDto), ArticleCreateDto.class)
                .exchange().expectStatus().isOk();
    }
}