package com.eattogether.heytogether.domain;

import com.eattogether.heytogether.domain.vo.Point;

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

    public void join(final int point) {
        this.point = this.point.use(point);
    }

    public void accumulate(final int point) {
        this.point = this.point.accumulate(point);
    }

    public int getPoint() {
        return point.getPoint();
    }
}
