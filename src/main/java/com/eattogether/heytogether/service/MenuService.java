package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.repository.MenuRepository;
import com.eattogether.heytogether.service.assembler.MenuAssembler;
import com.eattogether.heytogether.service.dto.MenuCreateDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuService {

    private MenuRepository menuRepository;

    private ShopService shopService;

    public MenuService(MenuRepository menuRepository, ShopService shopService) {
        this.menuRepository = menuRepository;
        this.shopService = shopService;
    }

    public void save(Long id, MenuCreateDto menuCreateDto) {
        Shop shop = shopService.findEntityBy(id);
        menuRepository.save(MenuAssembler.toEntity(menuCreateDto, shop));
    }
}
