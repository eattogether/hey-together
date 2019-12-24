package com.eattogether.heytogether.web.controller;

import com.eattogether.heytogether.service.AlarmService;
import com.eattogether.heytogether.service.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequestMapping("/api/alarm")
public class AlarmController {

    private AlarmService alarmService;

    public AlarmController(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @GetMapping("/articles/{id}/orders")
    public void signal(UserDto userDto, @PathVariable Long articleId) throws IOException {
        alarmService.sendEmitter(userDto, articleId);
    }

    @GetMapping("/articles")
    public SseEmitter handle(UserDto userDto, String orderId) {
        SseEmitter emitter = new SseEmitter();
        alarmService.setEmitter(userDto, new SseEmitter());

        emitter.onCompletion(() -> {
            synchronized (this) {
                alarmService.remove(orderId);
            }
        });

        emitter.onTimeout(() -> {
            emitter.complete();
        });

        return emitter;
    }
}