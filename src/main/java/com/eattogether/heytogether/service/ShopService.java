package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.repository.ShopRepository;
import com.eattogether.heytogether.service.assembler.ShopAssembler;
import com.eattogether.heytogether.service.dto.ShopCreateDto;
import com.eattogether.heytogether.service.dto.ShopInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@Service
@Transactional
public class ShopService {

    private ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public void save(ShopCreateDto shopCreateDto) {
        shopRepository.save(ShopAssembler.toEntity(shopCreateDto));
    }

    public ShopInfoDto findDtoBy(Long id) {
        return shopRepository.findById(id)
                .map(ShopAssembler::toDto)
                .orElseThrow(shopNotFoundExceptionWith(id));
    }

    public Shop findEntityBy(Long id) {
        return shopRepository.findById(id)
                .orElseThrow(shopNotFoundExceptionWith(id));
    }

    private Supplier<EntityNotFoundException> shopNotFoundExceptionWith(Long id) {
        return () -> new EntityNotFoundException("id가 " + id + "인 가게를 조회할 수 없습니다.");
    }
}
