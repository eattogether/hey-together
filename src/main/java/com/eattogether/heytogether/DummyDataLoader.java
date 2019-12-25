package com.eattogether.heytogether;

import com.eattogether.heytogether.domain.*;
import com.eattogether.heytogether.domain.repository.*;
import com.eattogether.heytogether.domain.vo.Money;
import com.eattogether.heytogether.domain.vo.Place;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Transactional
public class DummyDataLoader implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(DummyDataLoader.class);

    private ShopRepository shopRepository;
    private ShopMenuRepository shopMenuRepository;
    private UserRepository userRepository;
    private ArticleRepository articleRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public DummyDataLoader(ShopRepository shopRepository, ShopMenuRepository shopMenuRepository, UserRepository userRepository, ArticleRepository articleRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.shopRepository = shopRepository;
        this.shopMenuRepository = shopMenuRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        Shop shop_1 = new Shop(new Money(5000), new Money(18000), new Place(3.4, 5.6));
        shopRepository.save(shop_1);
        Shop shop_2 = new Shop(new Money(2000), new Money(18000), new Place(3.5, 5.7));
        shopRepository.save(shop_2);
        Shop shop_3 = new Shop(new Money(3000), new Money(12000), new Place(3.6, 5.8));
        shopRepository.save(shop_3);
        Shop shop_4 = new Shop(new Money(4000), new Money(18000), new Place(3.7, 5.9));
        shopRepository.save(shop_4);
        Shop shop_5 = new Shop(new Money(5000), new Money(7000), new Place(3.8, 5.10));
        shopRepository.save(shop_5);
        Shop shop_6 = new Shop(new Money(2000), new Money(13000), new Place(3.9, 5.11));
        shopRepository.save(shop_6);
        Shop shop_7 = new Shop(new Money(3000), new Money(14000), new Place(1.1, 2.12));
        shopRepository.save(shop_7);

        ShopMenu shopMenu_1 = new ShopMenu("떡볶이", new Money(15000), shop_1);
        shopMenuRepository.save(shopMenu_1);
        ShopMenu shopMenu_2 = new ShopMenu("오뎅", new Money(3000), shop_1);
        shopMenuRepository.save(shopMenu_2);
        shopMenuRepository.save(new ShopMenu("오징어 튀김", new Money(4000), shop_1));
        shopMenuRepository.save(new ShopMenu("김말이", new Money(4000), shop_1));
        shopMenuRepository.save(new ShopMenu("참치마요", new Money(3000), shop_1));

        shopMenuRepository.save(new ShopMenu("1인 직화", new Money(9000), shop_2));
        shopMenuRepository.save(new ShopMenu("2인 직화", new Money(15000), shop_2));
        shopMenuRepository.save(new ShopMenu("3인 직화", new Money(12000), shop_2));

        shopMenuRepository.save(new ShopMenu("짜장면", new Money(5000), shop_3));
        shopMenuRepository.save(new ShopMenu("짬뽕", new Money(4000), shop_3));
        shopMenuRepository.save(new ShopMenu("짬뽕밥", new Money(4000), shop_3));
        shopMenuRepository.save(new ShopMenu("탕수육", new Money(14000), shop_3));
        shopMenuRepository.save(new ShopMenu("유린기", new Money(14000), shop_3));
        shopMenuRepository.save(new ShopMenu("짬짜면", new Money(6000), shop_3));

        shopMenuRepository.save(new ShopMenu("김밥", new Money(3500), shop_4));
        shopMenuRepository.save(new ShopMenu("참치김밥", new Money(4000), shop_4));
        shopMenuRepository.save(new ShopMenu("띠드김밥", new Money(4000), shop_4));
        shopMenuRepository.save(new ShopMenu("돈까스김밥", new Money(4000), shop_4));
        shopMenuRepository.save(new ShopMenu("땡초김밥", new Money(4000), shop_4));
        shopMenuRepository.save(new ShopMenu("스페샬김밥", new Money(6000), shop_4));

        shopMenuRepository.save(new ShopMenu("1인 찜닭", new Money(13500), shop_5));
        shopMenuRepository.save(new ShopMenu("2인 찜닭", new Money(24000), shop_5));
        shopMenuRepository.save(new ShopMenu("3인 찜닭", new Money(34000), shop_5));
        shopMenuRepository.save(new ShopMenu("4인 찜닭", new Money(44000), shop_5));
        shopMenuRepository.save(new ShopMenu("치즈스틱", new Money(4000), shop_5));

        shopMenuRepository.save(new ShopMenu("후라이드", new Money(18000), shop_6));
        shopMenuRepository.save(new ShopMenu("뿌링클", new Money(19000), shop_6));
        shopMenuRepository.save(new ShopMenu("뿌링클 순살", new Money(20000), shop_6));
        shopMenuRepository.save(new ShopMenu("치즈볼", new Money(5000), shop_6));
        shopMenuRepository.save(new ShopMenu("뿌링 치즈볼", new Money(4000), shop_6));

        shopMenuRepository.save(new ShopMenu("황금 올리브", new Money(18000), shop_7));
        shopMenuRepository.save(new ShopMenu("닭 껍데기", new Money(3500), shop_7));
        shopMenuRepository.save(new ShopMenu("돼지 껍데기", new Money(5000), shop_7));
        shopMenuRepository.save(new ShopMenu("치즈볼", new Money(5000), shop_7));
        shopMenuRepository.save(new ShopMenu("치즈스틱", new Money(4000), shop_7));

        userRepository.save(new User("mamook1", "1234", 30000));
        userRepository.save(new User("mamook2", "1234", 30000));
        userRepository.save(new User("mamook3", "1234", 30000));

        Article article_1 = new Article("같이 먹어염ㅎㅎ", LocalDateTime.now(), new Place(1.1, 2.2), ArticleStatus.ACTIVE);
        articleRepository.save(article_1);

        Order order_1 = new Order(shop_1, article_1);
        orderRepository.save(order_1);

        orderItemRepository.save(new OrderItem(shopMenu_1, 2, order_1));
        orderItemRepository.save(new OrderItem(shopMenu_2, 1, order_1));

        logger.debug("Dummy DB Data successfully Added.");
    }
}
