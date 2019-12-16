package com.eattogether.heytogether.domain.repository;

import com.eattogether.heytogether.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
