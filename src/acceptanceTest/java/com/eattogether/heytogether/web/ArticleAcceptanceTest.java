package com.eattogether.heytogether.web;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.eattogether.heytogether.domain.vo.Place;
import com.eattogether.heytogether.service.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;


class ArticleAcceptanceTest extends LoggedClient {

    @Test
    @DisplayName("게시글 등록 성공")
    void save_article() {
        List<ItemCreateDto> items = Arrays.asList(new ItemCreateDto(1L, 1),
                new ItemCreateDto(2L, 1));
        ArticleCreateDto articleCreateDto = new ArticleCreateDto("같이묵자", LocalDateTime.now(), 1L,
                new Place(1, 1), items);

        webTestClient.post()
                .uri("/api/articles?userId=mamook")
                .header(JWT_HTTP_HEADER, loginHeader())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(articleCreateDto), ArticleCreateDto.class)
                .exchange().expectStatus().isOk()
                .expectBody(ArticleInfoDto.class)
                .consumeWith(document("articles/post",
                        requestFields(
                                fieldWithPath("title").description("게시글 제목"),
                                fieldWithPath("deadLine").description("마감 기한"),
                                fieldWithPath("shopId").description("가게 아이디"),
                                subsectionWithPath("place").description("위치"),
                                fieldWithPath("items.[0].menuId").description("주문하려는 메듀 아이디"),
                                fieldWithPath("items.[0].count").description("주문하려는 메뉴 개수")),
                        responseFields(
                                fieldWithPath("id").description("게시글 번호"),
                                fieldWithPath("title").description("최소 주문 금액"),
                                fieldWithPath("deadLine").description("최종 주문 금액"),
                                subsectionWithPath("place").description("최종 주문 금액"),
                                subsectionWithPath("articleStatus").description("게시글 상태")
                        )));
    }

    @Test
    @DisplayName("주문 정보를 제외한 게시글 조회 성공")
    void read_article() {
        webTestClient.get()
                .uri("/api/articles/1")
                .header(JWT_HTTP_HEADER, loginHeader())
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectBody(ArticleInfoDto.class)
                .consumeWith(document("articles/get",
                        responseFields(
                                fieldWithPath("id").description("게시글 아이디"),
                                fieldWithPath("title").description("최소 주문 금액"),
                                fieldWithPath("deadLine").description("최종 주문 금액"),
                                subsectionWithPath("place").description("최종 주문 금액"),
                                fieldWithPath("shopId").description("가게 아이디"),
                                fieldWithPath("userName").description("유저 아이디")
                        )));
    }

    @Test
    @DisplayName("주문 정보 조회 성공")
    void read_orders_by_article() {
        OrderDetailInfoDto expected = new OrderDetailInfoDto(5000, 18000, 33000);
        webTestClient.get().uri("/api/articles/1/orders")
                .header(JWT_HTTP_HEADER, loginHeader())
                .exchange().expectStatus().isOk()
                .expectBodyList(OrderDetailInfoDto.class).hasSize(2).contains(expected)
                .consumeWith(document("articles-orders/get",
                        responseFields(
                                fieldWithPath("[0].deliveryTip").description("배달 팁"),
                                fieldWithPath("[0].minimumOrderPrice").description("최소 주문 금액"),
                                fieldWithPath("[0].totalPrice").description("최종 주문 금액")
                        )));
    }


    @Test
    @DisplayName("게시 목록 조회 성공")
    void read_article_list() {
        webTestClient.get().uri("/api/articles")
                .header(JWT_HTTP_HEADER, loginHeader())
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectBody(ArticleInfosDto.class)
                .consumeWith(document("articles-search/get",
                        responseFields(
                                fieldWithPath("articleInfosDtoList.[0].id").description("배달 팁"),
                                fieldWithPath("articleInfosDtoList.[0].title").description("최소 주문 금액"),
                                fieldWithPath("articleInfosDtoList.[0].deadLine").description("최종 주문 금액"),
                                subsectionWithPath("articleInfosDtoList.[0].place").description("최종 주문 금액"),
                                subsectionWithPath("articleInfosDtoList.[0].shopId").description("가게 아이디"),
                                fieldWithPath("articleInfosDtoList.[0].userName").description("사용자 아이디")
                        )));
    }

    @Test
    @DisplayName("주문 참여하기 성공")
    void participate_order() {
        List<ItemCreateDto> items = Arrays.asList(new ItemCreateDto(1L, 1),
                new ItemCreateDto(2L, 1));

        ArticleParticipateDto articleParticipateDro = new ArticleParticipateDto(1L, items, 10000);

        webTestClient.post()
                .uri("/api/articles/1?userId=mamook")
                .header(JWT_HTTP_HEADER, loginHeader())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(articleParticipateDro), ArticleParticipateDto.class)
                .exchange().expectStatus().isOk()
                .expectBody()
                .consumeWith(document("articles-participate/post",
                        requestFields(
                                fieldWithPath("shopId").description("게시글 제목"),
                                fieldWithPath("items.[0].menuId").description("주문하려는 메듀 아이디"),
                                fieldWithPath("items.[0].count").description("주문하려는 메뉴 개수"),
                                fieldWithPath("totalPrice").description("주문하는 메뉴의 가격"))));
    }
}
