package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.User;
import com.eattogether.heytogether.domain.repository.OrderRepository;
import com.eattogether.heytogether.service.assembler.OrderAssembler;
import com.eattogether.heytogether.service.dto.OrderDetailInfoDto;
import com.eattogether.heytogether.service.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private OrderRepository orderRepository;
    private OrderItemService orderItemService;
    private UserService userService;

    public OrderService(OrderRepository orderRepository, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
    }

    public Order save(Shop shop, Article article, User user) {
        return orderRepository.save(new Order(shop, article, user));
    }

    public List<OrderDetailInfoDto> findByArticleId(Long articleId) {
        List<Order> orders = orderRepository.findAllByArticleId(articleId);

        return orders.stream()
                .map(order -> OrderAssembler.toDto(order, orderItemService.findByOrderId(order.getId())))
                .collect(Collectors.toList());
    }

    public List<Order> findOrdersByArticleId(Long articleId) {
        return orderRepository.findAllByArticleId(articleId);
    }
}
