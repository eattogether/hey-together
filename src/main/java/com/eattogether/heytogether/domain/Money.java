package com.eattogether.heytogether.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Money {
    @Column
    private int value;

    public Money() {
    }

    public Money(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
