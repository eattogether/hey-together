package com.eattogether.heytogether.domain.repository;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.ArticleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByArticleStatus(ArticleStatus articleStatus);
}
