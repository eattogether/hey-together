package com.eattogether.heytogether.service.assembler;

import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.domain.OrderItem;
import com.eattogether.heytogether.domain.ShopMenu;
import com.eattogether.heytogether.service.dto.ItemCreateDto;

public class OrderItemAssembler {

    public static OrderItem toEntity(ItemCreateDto itemCreateDto, ShopMenu shopMenu, Order order) {
        return new OrderItem(shopMenu.getName(), itemCreateDto.getCount(), order);
    }
}
