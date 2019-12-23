package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.ArticleStatus;
import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.repository.ArticleRepository;
import com.eattogether.heytogether.service.assembler.ArticleAssembler;
import com.eattogether.heytogether.service.dto.ArticleCreateDto;
import com.eattogether.heytogether.service.dto.ArticleInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class ArticleService {

    private ArticleRepository articleRepository;

    private ShopService shopService;
    private ItemService itemService;
    private OrderService orderService;

    public ArticleService(ArticleRepository articleRepository, ShopService shopService, ItemService itemService, OrderService orderService) {
        this.articleRepository = articleRepository;
        this.shopService = shopService;
        this.itemService = itemService;
        this.orderService = orderService;
    }

    public ArticleInfoDto saveArticle(ArticleCreateDto articleCreateDto) {
        Article article = articleRepository.save(ArticleAssembler.toEntity(articleCreateDto));

        Shop shop = shopService.findEntityBy(articleCreateDto.getShopId());
        Order order = orderService.save(shop, article);

        articleCreateDto.getItems().forEach(itemCreateDto -> itemService.save(itemCreateDto, order));

        return ArticleAssembler.toDto(article);
    }

    public ArticleInfoDto findById(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("id가 " + articleId + "인 메뉴를 조회할 수 없습니다."));

        return ArticleAssembler.toDto(article);
    }

    public List<ArticleInfoDto> findByActiveArticle() {
        List<Article> activeArticles = articleRepository.findByArticleStatus(ArticleStatus.ACTIVE);

        return Collections.unmodifiableList(activeArticles.stream()
                .map(article -> ArticleAssembler.toDto(article))
                .collect(Collectors.toList()));
    }
}
