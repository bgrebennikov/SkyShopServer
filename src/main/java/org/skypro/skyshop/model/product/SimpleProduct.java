package org.skypro.skyshop.model.product;

public class SimpleProduct extends Product {

    private final int price;

    public SimpleProduct(String title, int price) {
        super(title);

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
