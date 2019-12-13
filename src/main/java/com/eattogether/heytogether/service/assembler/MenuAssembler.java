package com.eattogether.heytogether.service.assembler;

import com.eattogether.heytogether.domain.Menu;
import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.service.dto.MenuCreateDto;
import com.eattogether.heytogether.service.dto.MenuInfoDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MenuAssembler {

    public static Menu toEntity(MenuCreateDto menuCreateDto, Shop shop) {
        return new Menu(menuCreateDto.getName(), menuCreateDto.getPrice(), shop);
    }

    public static List<MenuInfoDto> toDto(List<Menu> menus) {
        return menus.stream()
                .map(MenuAssembler::toDto)
                .collect(toList());
    }

    private static MenuInfoDto toDto(Menu menu) {
        return new MenuInfoDto(menu.getId(), menu.getName(),
                menu.getPrice(), menu.getShop().getId());
    }
}
