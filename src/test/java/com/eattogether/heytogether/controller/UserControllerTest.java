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

import static com.eattogether.heytogether.common.ControllerCookieHelper.JWT_COOKIE_NAME;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(profiles = "test")
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    private final static String JWT_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHBpcmUiOiIyMTE5LTEyLTEzVDE5OjA0OjE4LjUwMSIsImlzcyI6Im1hbW9vayIsInRpbWUiOiIyMDE5LTEyLTEzVDE5OjA0OjE4LjUwMSJ9.H685WnceIGgSx4WjHlv6V_i1p63ORLaU0Yt8R7rg_Uc";

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