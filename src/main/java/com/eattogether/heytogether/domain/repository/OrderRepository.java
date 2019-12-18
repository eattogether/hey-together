package com.eattogether.heytogether.domain.repository;

import java.util.List;

import com.eattogether.heytogether.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByArticleId(Long articleId);
}
