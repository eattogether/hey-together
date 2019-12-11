package com.eattogether.heytogether.service.assembler;

import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.service.dto.ShopCreateDto;

public class ShopAssembler {
    public static Shop toEntity(ShopCreateDto shopCreateDto) {
        return new Shop(shopCreateDto.getDeliveryTip(), shopCreateDto.getMinimumOrderPrice(),
                shopCreateDto.getPlace());
    }
}
