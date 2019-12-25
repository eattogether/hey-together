package com.eattogether.heytogether.service.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.ShopMenu;
import com.eattogether.heytogether.service.dto.MenuCreateDto;
import com.eattogether.heytogether.service.dto.ShopInfoDto;
import com.eattogether.heytogether.service.dto.ShopMenuInfoDto;
import com.eattogether.heytogether.service.dto.ShopMenuDetailInfoDto;

public class ShopMenuAssembler {

    public static ShopMenu toEntity(MenuCreateDto menuCreateDto, Shop shop) {
        return new ShopMenu(menuCreateDto.getName(), menuCreateDto.getPrice(), shop);
    }

    public static ShopMenuDetailInfoDto toDto(ShopInfoDto shopInfoDto, List<ShopMenu> shopMenus) {
        return new ShopMenuDetailInfoDto(shopInfoDto.getDeliveryTip().getValue(),
                shopInfoDto.getMinimumOrderPrice().getValue(), toDto(shopMenus));
    }

    private static List<ShopMenuInfoDto> toDto(List<ShopMenu> shopMenus) {
        return shopMenus.stream()
                        .map(shopMenu -> new ShopMenuInfoDto(shopMenu.getName(), shopMenu.getPrice().getValue()))
                        .collect(Collectors.toList());
    }
}
