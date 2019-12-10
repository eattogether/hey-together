package com.eattogether.heytogether.web.controller;

import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.service.MenuService;
import com.eattogether.heytogether.service.ShopService;
import com.eattogether.heytogether.service.dto.MenuCreateDto;
import com.eattogether.heytogether.service.dto.ShopCreateDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopApiController {
    private ShopService shopService;
    private MenuService menuService;

    public ShopApiController(ShopService shopService, MenuService menuService) {
        this.shopService = shopService;
        this.menuService = menuService;
    }

    @PostMapping("/api/shops")
    public ResponseEntity saveShop(@RequestBody ShopCreateDto shopCreateDto) {
        shopService.save(shopCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/shops/{id}")
    public ResponseEntity<Shop> readShop(@PathVariable Long id) {
        return ResponseEntity.ok(shopService.findBy(id));
    }

    @PostMapping("/api/shops/{id}/menus")
    public ResponseEntity saveMenu(@PathVariable Long id, @RequestBody MenuCreateDto menuCreateDto) {
        menuService.save(id, menuCreateDto);
        return ResponseEntity.ok().build();
    }
}
