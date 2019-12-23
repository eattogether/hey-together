package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.ShopMenu;
import com.eattogether.heytogether.domain.repository.ShopMenuRepository;
import com.eattogether.heytogether.service.assembler.MenuAssembler;
import com.eattogether.heytogether.service.dto.MenuCreateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class ShopMenuService {

    private ShopMenuRepository shopMenuRepository;
    private ShopService shopService;

    public ShopMenuService(ShopMenuRepository shopMenuRepository, ShopService shopService) {
        this.shopMenuRepository = shopMenuRepository;
        this.shopService = shopService;
    }

    public void save(Long shopId, MenuCreateDto menuCreateDto) {
        Shop shop = shopService.findEntityBy(shopId);
        shopMenuRepository.save(MenuAssembler.toEntity(menuCreateDto, shop));
    }

    public ShopMenu findById(Long menuId) {
        return shopMenuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("id가 " + menuId + "인 메뉴를 조회할 수 없습니다."));
    }
}
