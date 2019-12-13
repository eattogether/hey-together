package com.eattogether.heytogether.controller;

import com.eattogether.heytogether.controller.dto.LoginDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.eattogether.heytogether.common.ControllerCookieHelper.JWT_COOKIE_NAME;
import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    private final static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwidGltZSI6IjE1MTYyMzkwMjIifQ.LgwOkByjVf7hwCXe9iwaH1jWpjmbVn50aJCtqNlbxec";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("로그인 성공 테스트")
    void login_is_ok() {
        webTestClient.method(HttpMethod.POST)
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new LoginDto("mamook", "1234")), LoginDto.class)
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
                .cookie(JWT_COOKIE_NAME, token)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(entityExchangeResult -> {
                    assertThat(entityExchangeResult.getResponseCookies().getFirst(JWT_COOKIE_NAME).getMaxAge().getSeconds()).isEqualTo(0);
                })
        ;
    }
}