package com.eattogether.heytogether.web.controller;

import com.eattogether.heytogether.controller.dto.LoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
public class UserControllerTest {

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp(ApplicationContext applicationContext, RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
                .configureClient()
                .filter(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("로그인 테스트")
    void login_test() {
        this.webTestClient.method(HttpMethod.POST)
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new LoginDto("mamookja", "1234")), LoginDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().consumeWith(
                        document("login/post",
                        requestFields(
                                fieldWithPath("userId").description("로그인 할 Id"),
                                fieldWithPath("password").description("해당 ID의 비밀번호")))
                );
    }
}
