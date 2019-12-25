package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.ShopMenu;
import com.eattogether.heytogether.domain.repository.ShopMenuRepository;
import com.eattogether.heytogether.service.assembler.ShopMenuAssembler;
import com.eattogether.heytogether.service.dto.MenuCreateDto;
import com.eattogether.heytogether.service.dto.ShopInfoDto;
import com.eattogether.heytogether.service.dto.ShopMenuDetailInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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
        shopMenuRepository.save(ShopMenuAssembler.toEntity(menuCreateDto, shop));
    }

    public ShopMenu findById(Long menuId) {
        return shopMenuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("id가 " + menuId + "인 메뉴를 조회할 수 없습니다."));
    }

    public ShopMenuDetailInfoDto findShopMenuByShopId(Long shopId) {
        ShopInfoDto shopInfoDto = shopService.findDtoBy(shopId);
        List<ShopMenu> shopMenus = shopMenuRepository.findAllByShopId(shopId);

        return ShopMenuAssembler.toDto(shopInfoDto, shopMenus);
    }

    public ShopMenuDetailInfoDto findShopMenuByShopName(final String shopName) {
        ShopInfoDto shopInfoDto = shopService.findDtoBy(shopName);
        List<ShopMenu> shopMenus = shopMenuRepository.findAllByShopName(shopName);

        return ShopMenuAssembler.toDto(shopInfoDto, shopMenus);
    }
}
