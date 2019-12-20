package com.eattogether.heytogether.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private LocalDateTime deadLine;

    private Place place;

    public Article() {
    }

    public Article(String title, LocalDateTime deadLine, Place place) {
        this.title = title;
        this.deadLine = deadLine;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public Place getPlace() {
        return place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Article)) {
            return false;
        }
        final Article article = (Article) o;
        return Objects.equals(getId(), article.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", deadLine=" + deadLine +
                ", place=" + place +
                '}';
    }
}
