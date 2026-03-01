package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {

    private final int price;
    private final int discountPercent;

    public DiscountedProduct(UUID id, String title, int price, int discountPercent) {
        super(id, title);

        if (price <= 0) {
            throw new IllegalArgumentException("price cannot be zero or negative");
        }
        if (discountPercent < 0 ||  discountPercent > 100) {
            throw new IllegalArgumentException("discountPercent must be between 0 and 100");
        }

        this.price = price;
        this.discountPercent = discountPercent;
    }


    @Override
    public double getPrice() {
        return price - (price * ((double) discountPercent / 100));
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "%s со скидкой: %s%n".formatted(this.getTitle(), this.getPrice());
    }
}
