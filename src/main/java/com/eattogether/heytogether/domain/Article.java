package com.eattogether.heytogether.domain;

import com.eattogether.heytogether.domain.vo.Place;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private ArticleStatus articleStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User author;

    public Article() {
    }

    public Article(final String title, final LocalDateTime deadLine, final Place place, final ArticleStatus articleStatus, final User author) {
        this.title = title;
        this.deadLine = deadLine;
        this.place = place;
        this.articleStatus = articleStatus;
        this.author = author;
    }

    public Article(String title, LocalDateTime deadLine, Place place, User author) {
        this(title, deadLine, place, ArticleStatus.ACTIVE, author);
    }

    public boolean isAuthor(User user) {
        return author.equals(user);
    }

    public boolean isEnded(final LocalDateTime nowTime) {
        return deadLine.isBefore(nowTime);
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

    public ArticleStatus getArticleStatus() {
        return articleStatus;
    }

    public User getAuthor() {
        return author;
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
                ", articleStatus=" + articleStatus +
                ", user=" + author +
                '}';
    }
}
