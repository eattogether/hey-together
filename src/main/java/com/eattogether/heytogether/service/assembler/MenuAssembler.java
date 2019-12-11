package com.eattogether.heytogether.service.assembler;

import com.eattogether.heytogether.domain.Menu;
import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.service.dto.MenuCreateDto;

public class MenuAssembler {
    public static Menu toEntity(MenuCreateDto menuCreateDto, Shop shop) {
        return new Menu(menuCreateDto.getName(), menuCreateDto.getPrice(), shop);
    }
}
