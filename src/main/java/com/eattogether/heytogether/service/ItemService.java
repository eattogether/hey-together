package com.eattogether.heytogether.service;


import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.domain.OrderItem;
import com.eattogether.heytogether.domain.ShopMenu;
import com.eattogether.heytogether.domain.repository.OrderItemRepository;
import com.eattogether.heytogether.service.assembler.ItemAssembler;
import com.eattogether.heytogether.service.dto.ItemCreateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemService {

    private OrderItemRepository itemRepository;
    private ShopMenuService menuService;

    public ItemService(OrderItemRepository itemRepository, ShopMenuService menuService) {
        this.itemRepository = itemRepository;
        this.menuService = menuService;
    }

    public OrderItem save(ItemCreateDto itemCreateDto, Order order) {
        ShopMenu menu = menuService.findById(itemCreateDto.getMenuId());
        return itemRepository.save(ItemAssembler.toEntity(itemCreateDto, menu, order));
    }

    public List<OrderItem> findByOrderId(Long orderId) {
        return itemRepository.findAllByOrderId(orderId);
    }
}
