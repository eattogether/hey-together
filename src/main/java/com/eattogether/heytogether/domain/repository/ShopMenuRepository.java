package com.eattogether.heytogether.domain.repository;

import com.eattogether.heytogether.domain.ShopMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopMenuRepository extends JpaRepository<ShopMenu, Long> {

    List<ShopMenu> findAllByShopId(Long id);

    List<ShopMenu> findAllByShopName(String shopName);
}
