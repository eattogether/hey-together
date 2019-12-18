package com.eattogether.heytogether.domain.repository;


import com.eattogether.heytogether.service.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(final String userId);
}