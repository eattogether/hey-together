package com.eattogether.heytogether.service;

import javax.persistence.EntityNotFoundException;

import com.eattogether.heytogether.domain.vo.Money;
import com.eattogether.heytogether.domain.repository.ShopMenuRepository;
import com.eattogether.heytogether.service.dto.MenuCreateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.test.context.junit.jupiter.SpringExtension;

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
}