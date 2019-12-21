package com.eattogether.heytogether.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Money {

    public static Money ZERO = new Money(0);

    @Column
    private int value;

    public Money() {
    }

    public Money(int value) {
        this.value = value;
    }

    public Money plus(int value) {
        return new Money(this.value + value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        final Money money = (Money) o;
        return getValue() == money.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }
}
