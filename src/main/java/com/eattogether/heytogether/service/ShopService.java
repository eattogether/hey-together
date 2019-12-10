package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.repository.ShopRepository;
import com.eattogether.heytogether.service.dto.ShopCreateDto;

import org.springframework.stereotype.Service;

@Service
public class ShopService {
    private ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public void save(ShopCreateDto shopCreateDto) {
        shopRepository.save(shopCreateDto.toEntity());
    }
}
