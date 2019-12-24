package com.eattogether.heytogether.web.controller;

import com.eattogether.heytogether.service.AlarmService;
import com.eattogether.heytogether.service.dto.UserDto;
import org.apache.logging.log4j.util.Strings;
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
    private ExecutorService nonBlockingService = Executors
            .newCachedThreadPool();

    public AlarmController(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @GetMapping("/api/alarm/articles/{id}/orders")
    public void signal(UserDto userDto, @PathVariable("id") Long articleId) throws IOException {
        System.out.println("요청 왔다." + userDto.getUserId());
        alarmService.sendEmitter(userDto, articleId);
    }

    @GetMapping("/alarm/users/{userId}")
    public SseEmitter handle(@PathVariable("userId") String userId) {
        SseEmitter emitter = new SseEmitter();
        nonBlockingService.execute(() -> {
            try {
                String message = alarmService.getMessage(userId);
                if (Strings.isNotBlank(message)) {
                    emitter.send("/sse" + " @ " + message);
                }
                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });

        return emitter;
    }
}