package org.skypro.skyshop.model.product;

public class FixedPriceProduct extends Product {

    private final static double FIXED_PRICE_PRODUCT = 350;

    public FixedPriceProduct(String title) {
        super(title);
    }


    @Override
    public double getPrice() {
        return FIXED_PRICE_PRODUCT;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "%s с фиксированной ценой: %s%n".formatted(this.getTitle(), this.getPrice());
    }
}
