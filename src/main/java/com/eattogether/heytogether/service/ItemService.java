package com.eattogether.heytogether.service;


import com.eattogether.heytogether.domain.Item;
import com.eattogether.heytogether.domain.Menu;
import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.domain.repository.ItemRepository;
import com.eattogether.heytogether.service.assembler.ItemAssembler;
import com.eattogether.heytogether.service.dto.ItemCreateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemService {

    private ItemRepository itemRepository;

    private MenuService menuService;

    public ItemService(ItemRepository itemRepository, MenuService menuService) {
        this.itemRepository = itemRepository;
        this.menuService = menuService;
    }

    public Item save(ItemCreateDto itemCreateDto, Order order) {
        Menu menu = menuService.findById(itemCreateDto.getMenuId());
        return itemRepository.save(ItemAssembler.toEntity(itemCreateDto, menu, order));
    }

    public List<Item> findByOrderId(Long orderId) {
        return itemRepository.findAllByOrderId(orderId);
    }
}
