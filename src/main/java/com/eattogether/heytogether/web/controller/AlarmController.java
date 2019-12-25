package com.eattogether.heytogether.web.controller;

import com.eattogether.heytogether.service.AlarmService;
import com.eattogether.heytogether.service.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class AlarmController {

    private AlarmService alarmService;

    public AlarmController(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @GetMapping("/api/alarm/articles/{id}/orders")
    public void signal(UserDto userDto, @PathVariable("id") Long articleId) throws IOException {
        System.out.println("요청 왔다." + userDto.getUserId());
        alarmService.sendArrivedEmitter(userDto, articleId);
    }

    @GetMapping("/alarm/users/{userId}")
    public SseEmitter handle(@PathVariable("userId") String userId) {
        SseEmitter emitter = new SseEmitter(60L * 60L * 60L);

        emitter.onCompletion(() -> {
            alarmService.removeEmitter(userId);
        });

        emitter.onTimeout(() -> {
            emitter.complete();
            alarmService.removeEmitter(userId);
        });

        alarmService.setEmitter(userId, emitter);

        return emitter;
    }
}