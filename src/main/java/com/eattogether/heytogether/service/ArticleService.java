package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.*;
import com.eattogether.heytogether.domain.repository.ArticleRepository;
import com.eattogether.heytogether.service.assembler.ArticleAssembler;
import com.eattogether.heytogether.service.dto.ArticleCreateDto;
import com.eattogether.heytogether.service.dto.ArticleInfoDto;
import com.eattogether.heytogether.service.dto.ArticleParticipateDto;
import com.eattogether.heytogether.service.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleService {

    private ArticleRepository articleRepository;
    private ShopService shopService;
    private OrderItemService orderItemService;
    private OrderService orderService;
    private UserService userService;

    public ArticleService(final ArticleRepository articleRepository, final ShopService shopService, final OrderItemService orderItemService, final OrderService orderService, final UserService userService) {
        this.articleRepository = articleRepository;
        this.shopService = shopService;
        this.orderItemService = orderItemService;
        this.orderService = orderService;
        this.userService = userService;
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

    public List<ArticleInfoDto> findByActiveArticle() {
        List<Article> activeArticles = articleRepository.findByArticleStatus(ArticleStatus.ACTIVE);

        return Collections.unmodifiableList(activeArticles.stream()
                .map(article -> ArticleAssembler.toDto(article))
                .collect(Collectors.toList()));
    }

    public void participate(final Long id, final UserDto userDto, final ArticleParticipateDto articleParticipateDro) {
        Article article = articleRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Shop shop = shopService.findEntityBy(articleParticipateDro.getShopId());

        Order order = orderService.save(shop, article);
        articleParticipateDro.getItems().forEach(itemCreateDto -> orderItemService.save(itemCreateDto, order));

        User user = userService.findUserBy(userDto);
        user.participate(articleParticipateDro.getTotalPrice());
    }
}
