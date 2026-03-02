package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;


@SessionScope
@Component
public class ProductBasket {

    private final Map<UUID, Integer> productBasketItems;

    public ProductBasket() {
        this.productBasketItems = new HashMap<>();
    }

    public void add(UUID productId) {
        productBasketItems.merge(
                productId,
                1, Integer::sum
        );
    }

    public Map<UUID, Integer> getAll() {
        return Collections.unmodifiableMap(productBasketItems);
    }


}
