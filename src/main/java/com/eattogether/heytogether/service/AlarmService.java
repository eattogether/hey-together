package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Article;
import com.eattogether.heytogether.domain.Order;
import com.eattogether.heytogether.domain.User;
import com.eattogether.heytogether.service.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AlarmService {

    private Map<String, SseEmitter> userEmitters = new ConcurrentHashMap<>();
    private UserService userService;
    private ArticleService articleService;
    private OrderService orderService;

    public AlarmService(UserService userService, ArticleService articleService, OrderService orderService) {
        this.userService = userService;
        this.articleService = articleService;
        this.orderService = orderService;
    }

    public void setEmitter(String userId, SseEmitter sseEmitter) {
        userEmitters.put(userId, sseEmitter);
    }

    public void sendEmitter(String userId, String message) {
        if (!userEmitters.containsKey(userId)) {
            return;
        }

        SseEmitter sseEmitter = userEmitters.get(userId);
        try {
            sseEmitter.send(message);
            sseEmitter.complete();
        } catch (Exception ex) {
            sseEmitter.completeWithError(ex);
        }
    }

    public void sendArrivedEmitter(UserDto userDto, Long articleId) throws IOException {
        User user = userService.findUserBy(userDto);
        Article article = articleService.findArticleById(articleId);

        if (!article.isAuthor(user)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        for (Order order : orderService.findOrdersByArticleId(articleId)) {
            String orderUserId = order.getUser().getUserId();
            sendEmitter(orderUserId, "배달이 도착했습니다.");
        }
    }

    public void sendArticleEndedEmitter(UserDto userDto) {
        sendEmitter(userDto.getUserId(), "주문이 완료되었습니다.");
    }

    public void removeEmitter(String userId) {
        userEmitters.remove(userId);
    }
}
