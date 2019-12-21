package com.eattogether.heytogether.domain.repository;

import com.eattogether.heytogether.domain.ShopMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopMenuRepository extends JpaRepository<ShopMenu, Long> {

    Optional<ShopMenu> findByName(String menuName);
}
