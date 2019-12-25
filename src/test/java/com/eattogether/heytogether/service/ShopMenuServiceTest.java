package com.eattogether.heytogether.service;

import java.util.Arrays;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import com.eattogether.heytogether.domain.Shop;
import com.eattogether.heytogether.domain.ShopMenu;
import com.eattogether.heytogether.domain.repository.ShopMenuRepository;
import com.eattogether.heytogether.domain.vo.Money;
import com.eattogether.heytogether.domain.vo.Place;
import com.eattogether.heytogether.service.assembler.ShopAssembler;
import com.eattogether.heytogether.service.dto.MenuCreateDto;
import com.eattogether.heytogether.service.dto.ShopInfoDto;
import com.eattogether.heytogether.service.dto.ShopMenuDetailInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ShopMenuServiceTest {

    @InjectMocks
    private ShopMenuService shopMenuService;

    @Mock
    private ShopMenuRepository shopMenuRepository;

    @Mock
    private ShopService shopService;

    @Test
    @DisplayName("메뉴 추가 성공")
    void save_menu() {
        MenuCreateDto menuCreateDto = new MenuCreateDto("pizza", new Money(23000));

        shopMenuService.save(1L, menuCreateDto);
        verify(shopMenuRepository, Mockito.times(1)).save(any());
    }

    @Test
    @DisplayName("존재하지 않는 가게의 id에 메뉴 추가 시 예외 발생")
    void save_menu_with_shopNotFoundException() {
        MenuCreateDto menuCreateDto = new MenuCreateDto("pizza", new Money(23000));
        given(shopService.findEntityBy(-1L)).willThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> shopMenuService.save(-1L, menuCreateDto));
    }

    @Test
    @DisplayName("메뉴 조회 성공")
    void find_menu() {
        Shop shop = new Shop(new Money(2000), new Money(15000), new Place(1.1, 3.3));
        ReflectionTestUtils.setField(shop, "id", 1L);

        ShopMenu menu = new ShopMenu("떡볶이", new Money(1000), shop);
        given(shopMenuRepository.findById(1L)).willReturn(Optional.of(menu));

        assertThat(shopMenuService.findById(1L)).isInstanceOf(ShopMenu.class);
    }

    @Test
    @DisplayName("존재하지 않는 메뉴 조회 시 예외 발생")
    void find_menu_with_entityNotFoundException() {
        given(shopMenuRepository.findById(-1L)).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> shopMenuService.findById(-1L));
    }

    @Test
    @DisplayName("가게 번호로 메뉴 조회 성공")
    void find_menu_with_shop_id() {
        Shop shop = new Shop(new Money(2000), new Money(15000), new Place(1.1, 3.3));
        ReflectionTestUtils.setField(shop, "id", 1L);

        ShopInfoDto shopInfoDto = ShopAssembler.toDto(shop);

        ShopMenu shopMenu1 = new ShopMenu("떡볶이", new Money(1000), shop);
        ReflectionTestUtils.setField(shopMenu1, "id", 1L);

        ShopMenu shopMenu2 = new ShopMenu("피자", new Money(2000), shop);
        ReflectionTestUtils.setField(shopMenu2, "id", 2L);

        given(shopService.findDtoBy(1L)).willReturn(shopInfoDto);
        given(shopMenuRepository.findAllByShopId(1L)).willReturn(Arrays.asList(shopMenu1, shopMenu2));

        assertThat(shopMenuService.findShopMenuByShopId(1L)).isInstanceOf(ShopMenuDetailInfoDto.class);
    }
}