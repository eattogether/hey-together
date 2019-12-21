package com.eattogether.heytogether.service.assembler;

import com.eattogether.heytogether.domain.ShopMenu;
import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.service.dto.MenuCreateDto;

public class MenuAssembler {

    public static ShopMenu toEntity(MenuCreateDto menuCreateDto, Shop shop) {
        return new ShopMenu(menuCreateDto.getName(), menuCreateDto.getPrice(), shop);
    }
}
