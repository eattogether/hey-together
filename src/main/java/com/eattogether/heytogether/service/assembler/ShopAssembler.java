package com.eattogether.heytogether.service.assembler;

import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.service.dto.ShopCreateDto;
import com.eattogether.heytogether.service.dto.ShopInfoDto;

public class ShopAssembler {

    public static Shop toEntity(ShopCreateDto shopCreateDto) {
        return new Shop(
                shopCreateDto.getDeliveryTip(),
                shopCreateDto.getMinimumOrderPrice(),
                shopCreateDto.getPlace(),
                shopCreateDto.getName()
        );
    }

    public static ShopInfoDto toDto(Shop shop) {
        return new ShopInfoDto(
                shop.getId(),
                shop.getDeliveryTip(),
                shop.getMinimumOrderPrice(),
                shop.getPlace()
        );
    }
}
