package com.eattogether.heytogether.service.assembler;

import java.util.List;

import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.service.dto.OrderInfoDto;

import static java.util.stream.Collectors.toList;

public class OrderAssembler {
    public static List<OrderInfoDto> toDto(List<Order> orders) {
        return orders.stream()
                .map(order -> new OrderInfoDto(order.getId(),
                        order.getShop().getId(), order.getArticle().getId()))
                .collect(toList());
    }
}