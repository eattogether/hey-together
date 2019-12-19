package com.eattogether.heytogether.domain;

import com.eattogether.heytogether.domain.vo.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PointTest {

    @Test
    @DisplayName("포인트 적립 성공 테스트")
    void pointAccumulation() {
        Point point = new Point(10_000);
        Point accumulatedPoint = point.accumulate(20_000);

        assertThat(accumulatedPoint).isEqualTo(new Point(30_000));
    }

    @Test
    @DisplayName("포인트 적립 실패 테스트 남은 포인트 보다 사용 금액이 많은 경우")
    void pointAccumulation2() {
        Point point = new Point(10_000);
        assertThrows(IllegalArgumentException.class, () -> point.use(20_000));
    }
}
