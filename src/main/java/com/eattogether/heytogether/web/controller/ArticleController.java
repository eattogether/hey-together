package com.eattogether.heytogether.web.controller;

import com.eattogether.heytogether.service.ArticleService;
import com.eattogether.heytogether.service.OrderService;
import com.eattogether.heytogether.service.dto.ArticleCreateDto;
import com.eattogether.heytogether.service.dto.ArticleInfoDto;

import com.eattogether.heytogether.service.dto.ArticleInfosDto;
import org.springframework.beans.factory.annotation.Autowired;
import com.eattogether.heytogether.service.dto.OrderDetailInfoDto;
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

    @PostMapping("/api/articles")
    public ResponseEntity saveArticle(@RequestBody ArticleCreateDto articleCreateDto) {
        articleService.saveArticle(articleCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleInfoDto> readArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.findById(id));
    }

    @GetMapping("/api/articles/{id}/orders")
    public ResponseEntity<List<OrderDetailInfoDto>> readOrders(@PathVariable Long id) {
        List<OrderDetailInfoDto> orderDetailInfoDtos = orderService.findByArticleId(id);
        return ResponseEntity.ok(orderDetailInfoDtos);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<ArticleInfosDto> searchArticle() {
        return ResponseEntity.ok(new ArticleInfosDto(articleService.findByActiveArticle()));
    }
}
