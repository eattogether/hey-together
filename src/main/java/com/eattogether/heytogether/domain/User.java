package com.eattogether.heytogether.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String password;

    @Embedded
    private Point point = Point.DEFAULT_POINT;

    public User() {
    }

    public User(final String userId, final String password) {
        this.userId = userId;
        this.password = password;
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

}
