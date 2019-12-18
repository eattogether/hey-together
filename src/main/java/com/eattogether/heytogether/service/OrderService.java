package com.eattogether.heytogether.service;

import java.util.List;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.repository.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Shop shop, Article article) {
        return orderRepository.save(new Order(shop, article));
    }

    public List<Order> findByArticleId(Long articleId) {
        return orderRepository.findByArticleId(articleId);
    }
}
