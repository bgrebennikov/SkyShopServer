package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public UserBasket getUserBasket() {
        return new UserBasket(
                productBasket.getAll().entrySet().stream()
                        .map(entry -> {
                            UUID productId = entry.getKey();
                            int quantity = entry.getValue();

                            Product product = storageService
                                    .getProductById(productId)
                                    .orElseThrow(() ->
                                            new IllegalStateException("Product not found [ID: %s]".formatted(productId)));
                            return new BasketItem(product, quantity);
                        })
                        .toList()
        );
    }

    public void addProductById(UUID productId) {
        storageService.getProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: ID:" + productId));
    }

}

