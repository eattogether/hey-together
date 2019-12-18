package com.eattogether.heytogether;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class HeytogetherApplication {
    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(HeytogetherApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}
