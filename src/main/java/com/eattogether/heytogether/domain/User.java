package com.eattogether.heytogether.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    private static final int DEFAULT_POINT = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
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

    public void participate(final int totalPrice) {
        if (point < totalPrice) {
            throw new IllegalArgumentException();
        }

        point -= totalPrice;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
