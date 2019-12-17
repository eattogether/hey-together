package com.eattogether.heytogether.domain.repository;

import com.eattogether.heytogether.domain.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
