package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {

    private final int price;

    public SimpleProduct(UUID id, String title, int price) {
        super(id, title);

        if (price <= 0) {
            throw new IllegalArgumentException("price cannot be zero or negative");
        }

        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}
