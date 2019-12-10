package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.repository.ShopRepository;
import com.eattogether.heytogether.service.dto.ShopCreateDto;
import com.eattogether.heytogether.service.exception.ShopNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopService {
    private ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public void save(ShopCreateDto shopCreateDto) {
        shopRepository.save(shopCreateDto.toEntity());
    }

    public Shop findBy(Long id) {
        return shopRepository.findById(id)
                .orElseThrow(() ->
                        new ShopNotFoundException("id가 " + id + "인 가게를 조회할 수 없습니다."));
    }
}
