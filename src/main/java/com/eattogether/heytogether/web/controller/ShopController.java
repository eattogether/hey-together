package com.eattogether.heytogether.web.controller;

import com.eattogether.heytogether.service.ShopMenuService;
import com.eattogether.heytogether.service.ShopService;
import com.eattogether.heytogether.service.dto.MenuCreateDto;
import com.eattogether.heytogether.service.dto.ShopCreateDto;
import com.eattogether.heytogether.service.dto.ShopInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopController {

    private ShopService shopService;
    private ShopMenuService shopMenuService;

    public ShopController(ShopService shopService, ShopMenuService shopMenuService) {
        this.shopService = shopService;
        this.shopMenuService = shopMenuService;
    }

    @PostMapping("/api/shops")
    public ResponseEntity saveShop(@RequestBody ShopCreateDto shopCreateDto) {
        shopService.save(shopCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/shops/{id}")
    public ResponseEntity<ShopInfoDto> readShop(@PathVariable Long id) {
        return ResponseEntity.ok(shopService.findDtoBy(id));
    }

    @PostMapping("/api/shops/{id}/menus")
    public ResponseEntity saveMenu(@PathVariable Long id, @RequestBody MenuCreateDto menuCreateDto) {
        shopMenuService.save(id, menuCreateDto);
        return ResponseEntity.ok().build();
    }
}
