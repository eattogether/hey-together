package com.eattogether.heytogether;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HeytogetherApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HeytogetherApplication.class)
                .run(args);
    }
}
