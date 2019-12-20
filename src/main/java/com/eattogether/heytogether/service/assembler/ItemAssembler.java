package com.eattogether.heytogether.service.assembler;

import com.eattogether.heytogether.domain.Item;
import com.eattogether.heytogether.domain.Menu;
import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.service.dto.ItemCreateDto;

public class ItemAssembler {
    public static Item toEntity(ItemCreateDto itemCreateDto, Menu menu, Order order) {
        return new Item(menu, itemCreateDto.getCount(), order);
    }
}
