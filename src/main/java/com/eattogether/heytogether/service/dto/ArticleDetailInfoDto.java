package com.eattogether.heytogether.service.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.eattogether.heytogether.domain.Place;

public class ArticleDetailInfoDto {

    private Long id;
    private String title;
    private LocalDateTime deadLine;
    private Place place;
    private List<OrderInfoDto> orderInfoDtos;

    public ArticleDetailInfoDto(Long id, String title, LocalDateTime deadLine, Place place, List<OrderInfoDto> orderInfoDto) {
        this.id = id;
        this.title = title;
        this.deadLine = deadLine;
        this.place = place;
        this.orderInfoDtos = orderInfoDto;
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

    public List<OrderInfoDto> getOrderInfoDtos() {
        return orderInfoDtos;
    }
}
