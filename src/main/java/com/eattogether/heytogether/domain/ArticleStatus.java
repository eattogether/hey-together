package com.eattogether.heytogether.domain;

public enum ArticleStatus {
    ACTIVE("Active"),
    WAITING("Waiting"),
    END("End");

    String statusName;

    ArticleStatus(String statusName) {
        this.statusName = statusName;
    }
}
