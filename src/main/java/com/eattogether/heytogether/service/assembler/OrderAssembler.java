package com.eattogether.heytogether.service.assembler;

import com.eattogether.heytogether.domain.Item;
import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.service.dto.OrderDetailInfoDto;
import com.eattogether.heytogether.service.dto.OrderInfoDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class OrderAssembler {
    public static List<OrderInfoDto> toDto(List<Order> orders) {
        return orders.stream()
                .map(order -> new OrderInfoDto(order.getId(),
                        order.getShop().getId(), order.getArticle().getId()))
                .collect(toList());
    }

    public static OrderDetailInfoDto toDto(Order order, List<Item> items) {
        int totalPrice = items.stream()
                .mapToInt(item -> item.getPrice().getValue())
                .sum();
        return new OrderDetailInfoDto(
                order.getShop().getDeliveryTip().getValue(),
                order.getShop().getMinimumOrderPrice().getValue(),
                totalPrice);
    }
}