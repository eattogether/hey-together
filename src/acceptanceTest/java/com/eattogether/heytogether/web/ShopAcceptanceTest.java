package com.eattogether.heytogether.web;

import com.eattogether.heytogether.domain.vo.Money;
import com.eattogether.heytogether.domain.vo.Place;
import com.eattogether.heytogether.service.dto.MenuCreateDto;
import com.eattogether.heytogether.service.dto.ShopCreateDto;
import com.eattogether.heytogether.service.dto.ShopMenuDetailInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.eattogether.TestConstant.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
class ShopAcceptanceTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080")
                .filter(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("가게 등록 성공")
    void save_shop() {
        ShopCreateDto shopCreateDto = new ShopCreateDto(new Money(2000), new Money(14000),
                new Place(1.1, 2.2));
        webTestClient.post().uri("/api/shops")
                .header(JWT_HTTP_HEADER, BEARER + JWT_TOKEN)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(shopCreateDto), ShopCreateDto.class)
                .exchange().expectStatus().isOk();
    }

    @Test
    @DisplayName("가게 조회 요청 시 해당 가게의 정보 반환")
    void read_shop() {
        webTestClient.get().uri("/api/shops/2")
                .header(JWT_HTTP_HEADER, BEARER + JWT_TOKEN)
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(2)
                .jsonPath("$.deliveryTip").isNotEmpty()
                .jsonPath("$.minimumOrderPrice").isNotEmpty()
                .jsonPath("$.place").isNotEmpty();
    }

    @Test
    @DisplayName("메뉴 등록 성공")
    void add_menu() {
        MenuCreateDto menuCreateDto = new MenuCreateDto("bread", new Money(1500));

        webTestClient.post().uri("/api/shops/1/menus")
                .header(JWT_HTTP_HEADER, BEARER + JWT_TOKEN)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(menuCreateDto), MenuCreateDto.class)
                .exchange().expectStatus().isOk();
    }

    @Test
    @DisplayName("가게의 메뉴 조회 성공")
    void read_menus() {
        webTestClient.get().uri("/api/shops/1/menus")
                .header(JWT_HTTP_HEADER, BEARER + JWT_TOKEN)
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectBody(ShopMenuDetailInfoDto.class)
                .consumeWith(document("shop-menus/get",
                        responseFields(
                                fieldWithPath("deliveryTip").description("배달 팁"),
                                fieldWithPath("minimumOrderPrice").description("최소 주문 금액"),
                                fieldWithPath("menus.[0].name").description("메뉴 이름"),
                                fieldWithPath("menus.[0].price").description("메뉴 가격"))));
    }
}