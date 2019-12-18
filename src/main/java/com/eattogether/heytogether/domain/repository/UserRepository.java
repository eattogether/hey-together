package com.eattogether.heytogether.domain.repository;


import java.util.Optional;

import com.eattogether.heytogether.service.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(final String userId);
}
