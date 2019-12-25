package com.eattogether.heytogether.domain;

import com.eattogether.heytogether.domain.vo.Place;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.eattogether.TestConstant.USER_NAME;
import static com.eattogether.TestConstant.USER_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;

class ArticleTest {

    @Test
    @DisplayName("마감기한 확인 테스트")
    void checkArticleIsEnd() {
        User user = new User(USER_NAME, USER_PASSWORD);

        Article article = new Article("닭", LocalDateTime.now(), new Place(3.1, 3.1), user);
        assertThat(article.isEnded(LocalDateTime.now().minusDays(1L))).isFalse();
    }
}