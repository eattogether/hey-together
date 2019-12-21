package com.eattogether.heytogether.service.assembler;

import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.ShopMenu;
import com.eattogether.heytogether.service.dto.MenuCreateDto;
import com.eattogether.heytogether.service.dto.MenuInfoDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ShopMenuAssembler {

    public static ShopMenu toEntity(MenuCreateDto menuCreateDto, Shop shop) {
        return new ShopMenu(menuCreateDto.getName(), menuCreateDto.getPrice(), shop);
    }

    public static List<MenuInfoDto> toDto(List<ShopMenu> shopMenus) {
        return shopMenus.stream()
                .map(ShopMenuAssembler::toDto)
                .collect(toList());
    }

    private static MenuInfoDto toDto(ShopMenu shopMenu) {
        return new MenuInfoDto(shopMenu.getId(), shopMenu.getName(),
                shopMenu.getPrice(), shopMenu.getShop().getId());
    }
}
