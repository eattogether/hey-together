package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.Money;
import com.eattogether.heytogether.domain.Place;
import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.repository.ShopRepository;
import com.eattogether.heytogether.service.dto.ShopCreateDto;
import com.eattogether.heytogether.service.dto.ShopInfoDto;
import com.eattogether.heytogether.service.exception.ShopNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ShopServiceTest {

    @InjectMocks
    private ShopService shopService;

    @Mock
    private ShopRepository shopRepository;

    @Test
    @DisplayName("가게 등록 성공")
    void save_shop() {
        ShopCreateDto shopCreateDto = new ShopCreateDto(new Money(2000),
                new Money(15000), new Place(1.1, 2.2));

        shopService.save(shopCreateDto);
        verify(shopRepository, Mockito.times(1)).save(any());
    }

    @Test
    @DisplayName("가게 id를 통해 가게 정보 dto 반환")
    void find_dto_by() {
        Shop shop = new Shop(new Money(2000), new Money(15000), new Place(1.1, 3.3));
        ReflectionTestUtils.setField(shop, "id", 1L);
        given(shopRepository.findById(1L)).willReturn(Optional.of(shop));

        assertThat(shopService.findDtoBy(1L)).isInstanceOf(ShopInfoDto.class);
    }

    @Test
    @DisplayName("가게 id를 통해 가게 정보 entity 반환")
    void find_entity_by() {
        Shop shop = new Shop(new Money(2000), new Money(15000), new Place(1.1, 3.3));
        ReflectionTestUtils.setField(shop, "id", 1L);
        given(shopRepository.findById(1L)).willReturn(Optional.of(shop));

        assertThat(shopService.findEntityBy(1L)).isInstanceOf(Shop.class);
    }

    @Test
    @DisplayName("가게 id가 존재하지 않을 때 예외 발생")
    void find_entity_by_throw_shopNotFoundExceptionWith() {
        given(shopRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ShopNotFoundException.class, () -> shopService.findEntityBy(1L));
    }

    @Test
    @DisplayName("가게 id가 존재하지 않을 때 예외 발생")
    void find_dto_by_throw_shopNotFoundExceptionWith() {
        given(shopRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ShopNotFoundException.class, () -> shopService.findDtoBy(1L));
    }
}