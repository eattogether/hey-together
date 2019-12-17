package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.repository.ArticleRepository;
import com.eattogether.heytogether.service.dto.ArticleCreateDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleService {

    private ArticleRepository articleRepository;

    private ShopService shopService;
    private MenuService menuService;

    public ArticleService(ArticleRepository articleRepository, ShopService shopService, MenuService menuService) {
        this.articleRepository = articleRepository;
        this.shopService = shopService;
        this.menuService = menuService;
    }

    public void saveArticle(ArticleCreateDto articleCreateDto) {
        Article article = new Article(
                articleCreateDto.getTitle(),
                articleCreateDto.getDeadLine(),
                articleCreateDto.getPlace()
        );

        Shop shop = shopService.findEntityBy(articleCreateDto.getShopId());
//        for (ItemCreateDto itemCreateDto : articleCreateDto.getItems()) {
//            Menu menu = menuService.findById(itemCreateDto.getMenuId());
//            order.addItem(new Item(menu, itemCreateDto.getCount()));
//        }
        article.addOrder(shop);

        articleRepository.save(article);
    }
}
