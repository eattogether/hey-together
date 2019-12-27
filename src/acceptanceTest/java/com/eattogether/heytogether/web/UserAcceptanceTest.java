package com.eattogether.heytogether.web;

import com.eattogether.heytogether.service.dto.LoginDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.eattogether.TestConstant.JWT_TOKEN;
import static com.eattogether.heytogether.common.ControllerCookieHelper.JWT_COOKIE_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

class UserAcceptanceTest extends LoggedClient {

    @Test
    @DisplayName("로그인 성공 테스트")
    void login_is_ok() {
        final WebTestClient.BodyContentSpec loginedBodyContentSpec = webTestClient.method(HttpMethod.POST)
                .uri("/login")
                .header(JWT_HTTP_HEADER,BEARER+JWT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new LoginDto(USER_NAME, USER_PASSWORD)), LoginDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody();


        loginedBodyContentSpec.consumeWith(entityExchangeResult -> {
            assertThat(entityExchangeResult.getResponseCookies().containsKey(JWT_COOKIE_NAME)).isTrue();
        });
        loginedBodyContentSpec.consumeWith(document("user-login",
                responseFields(fieldWithPath("userId").description("게시글 제목"))));
    }

    @Test
    @DisplayName("로그아웃 성공 테스트")
    void logout_is_ok() {
        webTestClient.method(HttpMethod.GET)
                .uri("/logout")
                .header(JWT_HTTP_HEADER, loginHeader())
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