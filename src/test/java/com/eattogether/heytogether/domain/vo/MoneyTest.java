package com.eattogether.heytogether.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyTest {

    @Test
    @DisplayName("금액 생성 성공 테스트")
    void create_money_success() {
        Money money = new Money(100);
        assertThat(money).isEqualTo(new Money(100));
    }

    @Test
    @DisplayName("금액 생성 실패 테스트 금액이 음수인 경우")
    void create_money_failed() {
        assertThrows(IllegalArgumentException.class, () -> new Money(-1));
    }

    @Test
    @DisplayName("금액 곱하기 테스트")
    void multiply_money_success() {
        Money money = new Money(100);
        assertThat(money.multiply(100)).isEqualTo(new Money(10_000));
    }
}