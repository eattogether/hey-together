package com.eattogether.heytogether.service;

import javax.persistence.EntityNotFoundException;

import com.eattogether.heytogether.domain.Menu;
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

    public void save(Long shopId, MenuCreateDto menuCreateDto) {
        Shop shop = shopService.findEntityBy(shopId);
        menuRepository.save(MenuAssembler.toEntity(menuCreateDto, shop));
    }

    public Menu findById(Long menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("id가 " + menuId + "인 메뉴를 조회할 수 없습니다."));
    }
}
