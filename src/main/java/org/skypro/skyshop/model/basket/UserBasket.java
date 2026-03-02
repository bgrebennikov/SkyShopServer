package org.skypro.skyshop.model.basket;


import java.util.List;

public final class UserBasket {
    private List<BasketItem> basketItems;
    private Integer total;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        this.total = basketItems.stream()
                .mapToInt(item ->
                        (int) (item.getProduct().getPrice() * item.getQuantity()))
                .sum();
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public Integer getTotal() {
        return total;
    }
}
