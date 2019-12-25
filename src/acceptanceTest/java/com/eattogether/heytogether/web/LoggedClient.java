package com.eattogether.heytogether.web;

import com.eattogether.heytogether.service.dto.LoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.eattogether.heytogether.common.ControllerCookieHelper.JWT_COOKIE_NAME;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
public class LoggedClient {

    public static final String JWT_HTTP_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String USER_NAME = "mamook";
    public static final String USER_PASSWORD = "1234";

    protected WebTestClient webTestClient;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080")
                .filter(documentationConfiguration(restDocumentation))
                .build();
    }

    public String loginHeader() {
        ResponseCookie responseCookie = webTestClient.method(HttpMethod.POST)
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new LoginDto(USER_NAME, USER_PASSWORD)), LoginDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .returnResult()
                .getResponseCookies()
                .get(JWT_COOKIE_NAME)
                .get(0);

        return BEARER + responseCookie.getValue();
    }
}
