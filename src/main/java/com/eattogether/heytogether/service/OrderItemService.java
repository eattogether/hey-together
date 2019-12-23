package com.eattogether.heytogether.service;


import com.eattogether.heytogether.domain.OrderItem;
import com.eattogether.heytogether.domain.ShopMenu;
import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.domain.repository.OrderItemRepository;
import com.eattogether.heytogether.service.assembler.ItemAssembler;
import com.eattogether.heytogether.service.dto.ItemCreateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderItemService {

    private OrderItemRepository orderItemRepository;
    private ShopMenuService shopMenuService;

    public OrderItemService(OrderItemRepository orderItemRepository, ShopMenuService shopMenuService) {
        this.orderItemRepository = orderItemRepository;
        this.shopMenuService = shopMenuService;
    }

    public OrderItem save(ItemCreateDto itemCreateDto, Order order) {
        ShopMenu shopMenu = shopMenuService.findById(itemCreateDto.getMenuId());
        return orderItemRepository.save(ItemAssembler.toEntity(itemCreateDto, shopMenu, order));
    }

    public List<OrderItem> findByOrderId(Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId);
    }
}
