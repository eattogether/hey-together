package com.eattogether.heytogether.web;

import com.eattogether.heytogether.domain.*;
import com.eattogether.heytogether.domain.repository.*;
import com.eattogether.heytogether.domain.vo.Money;
import com.eattogether.heytogether.domain.vo.Place;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Transactional
public class TestDataLoader implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(TestDataLoader.class);

    private ShopRepository shopRepository;
    private ShopMenuRepository shopMenuRepository;
    private ArticleRepository articleRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private UserRepository userRepository;

    public TestDataLoader(final ShopRepository shopRepository, final ShopMenuRepository shopMenuRepository, final ArticleRepository articleRepository, final OrderRepository orderRepository, final OrderItemRepository orderItemRepository, final UserRepository userRepository) {
        this.shopRepository = shopRepository;
        this.shopMenuRepository = shopMenuRepository;
        this.articleRepository = articleRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        User user1 = new User("mamook", "1234", 500_000);
        userRepository.save(user1);
        Shop shop_1 = new Shop(new Money(5000), new Money(18000), new Place(3.3, 5.5), "BHC1");
        shopRepository.save(shop_1);
        shopRepository.save(new Shop(new Money(3000), new Money(15000), new Place(1.1, 2.2), "BHC2"));

        ShopMenu shopMenu_1 = new ShopMenu("떡볶이", new Money(15000), shop_1);
        shopMenuRepository.save(shopMenu_1);
        ShopMenu shopMenu_2 = new ShopMenu("직화", new Money(25000), shop_1);
        shopMenuRepository.save(shopMenu_2);

        Article article_1 = new Article("같이 드실 분?", LocalDateTime.now(), new Place(1.1, 2.2), ArticleStatus.ACTIVE, user1);
        articleRepository.save(article_1);

        Order order_1 = new Order(shop_1, article_1);
        orderRepository.save(order_1);

        orderItemRepository.save(new OrderItem(shopMenu_1, 2, order_1));
        orderItemRepository.save(new OrderItem(shopMenu_2, 1, order_1));

        logger.debug("Test DB Data successfully Added.");
    }
}
