package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.domain.User;
import com.eattogether.heytogether.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AlarmService {

    private Map<String, String> messages = new ConcurrentHashMap<>();
    private UserService userService;
    private ArticleService articleService;
    private OrderService orderService;

    public AlarmService(UserService userService, ArticleService articleService, OrderService orderService) {
        this.userService = userService;
        this.articleService = articleService;
        this.orderService = orderService;
    }

    public String getMessage(String userId) {
        String message = messages.get(userId);
        messages.put(userId, "");

        return message;
    }

    public void sendEmitter(UserDto userDto, Long articleId) throws IOException {
        User user = userService.findUserBy(userDto);
        Article article = articleService.findArticleById(articleId);
        orderService.findByArticleId(articleId);

        if (!article.isAuthor(user)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        for (Order order : orderService.findOrdersByArticleId(articleId)) {
            String orderUserId = order.getUser().getUserId();
            if (messages.containsKey(order.getUser().getUserId())) {
                messages.put(orderUserId, "배달이 도착했습니다.");
            }
        }
    }

    public void sendEmitterArticleEnded(UserDto userDto) {
        messages.put(userDto.getUserId(), " 주문이 완료되었습니다.");
    }

    public void remove(String userId) {
        this.messages.remove(userId);
    }
}
