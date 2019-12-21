package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.repository.ArticleRepository;
import com.eattogether.heytogether.service.assembler.ArticleAssembler;
import com.eattogether.heytogether.service.dto.ArticleCreateDto;
import com.eattogether.heytogether.service.dto.ArticleInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class ArticleService {

    private ArticleRepository articleRepository;
    private ShopService shopService;
    private OrderItemService orderItemService;
    private OrderService orderService;

    public ArticleService(ArticleRepository articleRepository, ShopService shopService, OrderItemService orderItemService, OrderService orderService) {
        this.articleRepository = articleRepository;
        this.shopService = shopService;
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }

    public ArticleInfoDto saveArticle(ArticleCreateDto articleCreateDto) {
        Article article = articleRepository.save(ArticleAssembler.toEntity(articleCreateDto));

        Shop shop = shopService.findEntityBy(articleCreateDto.getShopId());
        Order order = orderService.save(shop, article);

        articleCreateDto.getItems().forEach(itemCreateDto -> orderItemService.save(itemCreateDto, order));

        return ArticleAssembler.toDto(article);
    }

    public ArticleInfoDto findById(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("id가 " + articleId + "인 메뉴를 조회할 수 없습니다."));

        return ArticleAssembler.toDto(article);
    }
}
