package org.skypro.skyshop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {


    @Mock
    private ProductBasket productBasket;

    @Mock
    StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    @DisplayName("Должен выбросить NoSuchProductException при добавлении несуществующего товара")
    void shouldThrowExceptionWhenProductNotFound() {
        UUID id = UUID.randomUUID();

        when(storageService.getProductById(id)).thenReturn(Optional.empty());
        assertThrows(NoSuchProductException.class, () -> basketService.addProductById(id));

        verifyNoInteractions(productBasket);
    }

    @Test
    @DisplayName("Должен добавить товар в корзину, если он существует в хранилище")
    void shouldAddProductWhenFound() {
        UUID id = UUID.randomUUID();
        Product mockProduct = mock(Product.class);

        when(storageService.getProductById(id)).thenReturn(Optional.of(mockProduct));
        basketService.addProductById(id);

        verify(productBasket, times(1)).add(id);
    }

    @Test
    @DisplayName("getUserBasket должен возвращать пустую корзину, если товаров нет")
    void shouldReturnEmptyBasket() {
        when(productBasket.getAll()).thenReturn(Collections.emptyMap());
        UserBasket result = basketService.getUserBasket();

        assertThat(result.getTotal()).isZero();
        assertThat(result.getBasketItems()).isEmpty();
    }

    @Test
    @DisplayName("getUserBasket должен возвращать заполненную корзину")
    void shouldReturnPopulatedBasket() {
        UUID id = UUID.randomUUID();
        Product mockProduct = mock(Product.class);
        when(mockProduct.getTitle()).thenReturn("Milk");

        when(productBasket.getAll()).thenReturn(Map.of(id, 2));
        when(storageService.getProductById(id)).thenReturn(Optional.of(mockProduct));

        UserBasket result = basketService.getUserBasket();

        assertThat(result.getBasketItems()).hasSize(1);
        assertThat(result.getBasketItems().get(0).getProduct().getTitle()).isEqualTo("Milk");
        assertThat(result.getBasketItems().get(0).getQuantity()).isEqualTo(2);
    }

}