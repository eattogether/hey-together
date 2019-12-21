package com.eattogether.heytogether.domain.repository;

import com.eattogether.heytogether.domain.ShopMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopMenuRepository extends JpaRepository<ShopMenu, Long> {
}
