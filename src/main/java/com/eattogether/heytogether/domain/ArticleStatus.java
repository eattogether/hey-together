package com.eattogether.heytogether.domain;

public enum ArticleStatus {
    ACTIVE("Active"),
    END("End");

    String statusName;

    ArticleStatus(String statusName) {
        this.statusName = statusName;
    }
}
