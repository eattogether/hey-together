package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.repository.MenuRepository;
import com.eattogether.heytogether.service.dto.MenuCreateDto;

import org.springframework.stereotype.Service;

@Service
public class MenuService {
    private MenuRepository menuRepository;

    private ShopService shopService;

    public MenuService(MenuRepository menuRepository, ShopService shopService) {
        this.menuRepository = menuRepository;
        this.shopService = shopService;
    }

    public void save(Long id, MenuCreateDto menuCreateDto) {
        Shop shop = shopService.findBy(id);
        menuRepository.save(menuCreateDto.toEntity(shop));
    }
}
