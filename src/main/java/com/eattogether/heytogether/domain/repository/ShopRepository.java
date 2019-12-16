package com.eattogether.heytogether.domain.repository;

import com.eattogether.heytogether.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
