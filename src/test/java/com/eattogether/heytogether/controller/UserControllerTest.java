package com.eattogether.heytogether.controller;

import com.eattogether.heytogether.service.dto.LoginDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.eattogether.TestConstant.*;
import static com.eattogether.heytogether.common.ControllerCookieHelper.JWT_COOKIE_NAME;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(profiles = "test")
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("로그인 성공 테스트")
    void login_is_ok() {
        webTestClient.method(HttpMethod.POST)
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new LoginDto(USER_NAME, USER_PASSWORD)), LoginDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(entityExchangeResult -> {
                    assertThat(entityExchangeResult.getResponseCookies().containsKey(JWT_COOKIE_NAME)).isTrue();
                })
        ;
    }

    @Test
    @DisplayName("로그아웃 성공 테스트")
    void logout_is_ok() {
        webTestClient.method(HttpMethod.GET)
                .uri("/logout")
                .header(JWT_HTTP_HEADER, BEARER + JWT_TOKEN)
                .cookie(JWT_COOKIE_NAME, JWT_TOKEN)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(entityExchangeResult -> {
                    assertThat(entityExchangeResult.getResponseCookies().getFirst(JWT_COOKIE_NAME).getMaxAge().getSeconds()).isEqualTo(0);
                })
        ;
    }
}