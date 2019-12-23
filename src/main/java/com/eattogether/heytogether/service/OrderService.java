package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.repository.OrderRepository;
import com.eattogether.heytogether.service.assembler.OrderAssembler;
import com.eattogether.heytogether.service.dto.OrderDetailInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private OrderRepository orderRepository;
    private ItemService itemService;

    public OrderService(OrderRepository orderRepository, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.itemService = itemService;
    }

    public Order save(Shop shop, Article article) {
        return orderRepository.save(new Order(shop, article));
    }

    public List<OrderDetailInfoDto> findByArticleId(Long articleId) {
        List<Order> orders = orderRepository.findAllByArticleId(articleId);

        return orders.stream()
                .map(order -> OrderAssembler.toDto(order, itemService.findByOrderId(order.getId())))
                .collect(Collectors.toList());
    }
}
