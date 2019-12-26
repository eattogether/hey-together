package com.eattogether.heytogether.web.controller;

import com.eattogether.heytogether.service.ArticleService;
import com.eattogether.heytogether.service.OrderService;
import com.eattogether.heytogether.service.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    private ArticleService articleService;
    private OrderService orderService;

    public ArticleController(ArticleService articleService, OrderService orderService) {
        this.articleService = articleService;
        this.orderService = orderService;
    }

    @PostMapping("/api/articles") //
    public ResponseEntity<ArticleInfoDto> saveArticle(@RequestBody ArticleCreateDto articleCreateDto, UserDto userDto) {
        return ResponseEntity.ok(articleService.saveArticle(articleCreateDto, userDto));
    }

    @GetMapping("/api/articles/{id}") //
    public ResponseEntity<ArticleInfoDtoWithShopId> readArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.findDtoById(id));
    }

    @GetMapping("/api/articles/{id}/orders") //
    public ResponseEntity<List<OrderDetailInfoDto>> readOrders(@PathVariable Long id) {
        List<OrderDetailInfoDto> orderDetailInfoDtos = orderService.findByArticleId(id);
        return ResponseEntity.ok(orderDetailInfoDtos);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<ArticleInfosWithShopDto> searchArticles() {
        return ResponseEntity.ok(new ArticleInfosWithShopDto(articleService.findByActiveArticle()));
    }

    @PostMapping("/api/articles/{id}")
    public ResponseEntity participate(@PathVariable Long id, UserDto userDto, @RequestBody  ArticleParticipateDto articleParticipateDro) {
        articleService.participate(id, userDto, articleParticipateDro);
        return ResponseEntity.ok().build();
    }
}
