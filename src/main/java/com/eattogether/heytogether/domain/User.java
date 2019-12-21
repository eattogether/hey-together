package com.eattogether.heytogether.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    private static final int DEFAULT_POINT = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;
    private String password;
    private int point;

    public User() {
    }

    public User(final String userId, final String password, final int point) {
        this.userId = userId;
        this.password = password;
        this.point = point;
    }

    public User(final String userId, final String password) {
        this(userId, password, DEFAULT_POINT);
    }

    public boolean checkPassword(final String password) {
        return this.password.equals(password);
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void participate(final int totalPrice) {
        if (point < totalPrice) {
            throw new IllegalArgumentException();
        }
        point -= totalPrice;
    }
}
