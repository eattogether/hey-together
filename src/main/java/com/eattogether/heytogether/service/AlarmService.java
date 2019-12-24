package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.domain.User;
import com.eattogether.heytogether.service.dto.OrderDetailInfoDto;
import com.eattogether.heytogether.service.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AlarmService {

    private Map<String, SseEmitter> emmitters = new ConcurrentHashMap<>();
    private UserService userService;
    private ArticleService articleService;
    private OrderService orderService;

    public AlarmService(UserService userService, ArticleService articleService, OrderService orderService) {
        this.userService = userService;
        this.articleService = articleService;
        this.orderService = orderService;
    }

    public void setEmitter(UserDto userDto, SseEmitter emitter) {
        emmitters.put(userDto.getUserId(), emitter);
    }

    public void sendEmitter(UserDto userDto, Long articleId) throws IOException {
        User user = userService.findUserBy(userDto);
        Article article = articleService.findArticleById(articleId);
        orderService.findByArticleId(articleId);

        if (!article.isAuthor(user)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        for (Order order: orderService.findOrdersByArticleId(articleId)) {
            String orderUserId = order.getUser().getUserId();
            if (emmitters.containsKey(order.getUser().getUserId())) {
                emmitters.get(orderUserId).send("배달이 도착했습니다.");
            }
        }
    }

    public void remove(String orderId) {
        this.emmitters.remove(orderId);
    }
}
