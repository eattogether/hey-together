package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.repository.OrderRepository;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private ShopService shopService;
    private MenuService menuService;

    public OrderService(OrderRepository orderRepository, ShopService shopService, MenuService menuService) {
        this.orderRepository = orderRepository;
        this.shopService = shopService;
        this.menuService = menuService;
    }

//    public Order save(Long shopId, List<ItemCreateDto> itemCreateDtos, Article article) {
//        Shop shop = shopService.findEntityBy(shopId);
//        Order order = orderRepository.save(new Order(shop));
//
//        List<Item> items = itemCreateDtos.stream()
//                .map(itemCreateDto -> new Item(menuService.findById(itemCreateDto.getMenuId()), itemCreateDto.getCount()))
//                .collect(Collectors.toList());
//
//        order.addItems(items);
//
//        return orderRepository.save(OrderAssembler.toEntity());
//    }
}
