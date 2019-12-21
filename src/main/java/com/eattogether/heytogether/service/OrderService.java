package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.domain.OrderItem;
import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class OrderService {

    private OrderRepository orderRepository;
    private OrderItemService orderItemService;

    public OrderService(final OrderRepository orderRepository, final OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
    }

    public Order save(Shop shop, Article article) {
        return orderRepository.save(new Order(shop, article));
    }

    public void addOrderItem(final Map<String, Integer> items, final Order order) {
        items.forEach((menuName, count) -> orderItemService.save(new OrderItem(menuName, count, order)));
    }
}


