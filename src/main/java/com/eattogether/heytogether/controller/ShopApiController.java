package com.eattogether.heytogether.controller;

import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.service.ShopService;
import com.eattogether.heytogether.service.dto.ShopCreateDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopApiController {
    private ShopService shopService;

    public ShopApiController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/api/shops")
    public ResponseEntity save(@RequestBody ShopCreateDto shopCreateDto) {
        shopService.save(shopCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/shops/{id}")
    public ResponseEntity<Shop> save(@PathVariable Long id) {
        return ResponseEntity.ok(shopService.findBy(id));
    }
}
