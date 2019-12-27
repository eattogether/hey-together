package com.eattogether.heytogether.web;

import com.eattogether.heytogether.domain.vo.Money;
import com.eattogether.heytogether.domain.vo.Place;
import com.eattogether.heytogether.service.dto.MenuCreateDto;
import com.eattogether.heytogether.service.dto.ShopCreateDto;
import com.eattogether.heytogether.service.dto.ShopInfoDto;
import com.eattogether.heytogether.service.dto.ShopMenuDetailInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

class ShopAcceptanceTest extends LoggedClient {

    @Test
    @DisplayName("가게 등록 성공")
    void save_shop() {
        ShopCreateDto shopCreateDto = new ShopCreateDto(new Money(2000), new Money(14000),
                new Place(1.1, 2.2), "오떡");
        webTestClient.post().uri("/api/shops")
                .header(JWT_HTTP_HEADER, loginHeader())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(shopCreateDto), ShopCreateDto.class)
                .exchange().expectStatus().isOk();
    }

    @Test
    @DisplayName("가게 조회 요청 시 해당 가게의 정보 반환")
    void read_shop() {
        webTestClient.get().uri("/api/shops/1")
                .header(JWT_HTTP_HEADER, loginHeader())
                .exchange().expectStatus().isOk()
                .expectBody(ShopInfoDto.class)
                .consumeWith(document("shops/get",
                        responseFields(
                                fieldWithPath("shopId").description("가게의 아이디"),
                                fieldWithPath("shopName").description("가게명"),
                                fieldWithPath("deliveryTip").description("배달 팁"),
                                fieldWithPath("minimumOrderPrice").description("최소 주문 금액"),
                                fieldWithPath("menus.[0].menuId").description("메뉴의 아이디"),
                                fieldWithPath("menus.[0].name").description("메뉴명"),
                                fieldWithPath("menus.[0].price").description("메뉴의 가격")
                        )));
    }

    @Test
    @DisplayName("메뉴 등록 성공")
    void add_menu() {
        MenuCreateDto menuCreateDto = new MenuCreateDto("bread", new Money(1500));

        webTestClient.post().uri("/api/shops/1/menus")
                .header(JWT_HTTP_HEADER, loginHeader())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(menuCreateDto), MenuCreateDto.class)
                .exchange().expectStatus().isOk();
    }

    @Test
    @DisplayName("가게의 메뉴 조회 성공")
    void read_menus() {
        webTestClient.get().uri("/api/shops/교촌치킨/menus")
                .header(JWT_HTTP_HEADER, loginHeader())
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectBody(ShopMenuDetailInfoDto.class)
                .consumeWith(document("shop-menus/get",
                        responseFields(
                                fieldWithPath("shopId").description("가게 아이디"),
                                fieldWithPath("shopName").description("가게명"),
                                fieldWithPath("deliveryTip").description("배달 팁"),
                                fieldWithPath("minimumOrderPrice").description("최소 주문 금액"),
                                fieldWithPath("menus.[0].menuId").description("메뉴 아이디"),
                                fieldWithPath("menus.[0].name").description("메뉴 이름"),
                                fieldWithPath("menus.[0].price").description("메뉴 가격"))));
    }
}