package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixedPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StorageService {

    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        products = buildProductsMap();
        articles = buildArticlesMap();
    }

    public Map<UUID, Product> getProducts() {
        return products;
    }

    public Map<UUID, Article> getArticles() {
        return articles;
    }

    private Map<UUID, Product> buildProductsMap() {
        Map<UUID, Product> productsMap = new HashMap<>();

        Product p1 = new SimpleProduct(UUID.randomUUID(), "Milk", 87);
        Product p2 = new FixedPriceProduct(UUID.randomUUID(), "Coconut");
        Product p3 = new DiscountedProduct(UUID.randomUUID(), "Banana", 170, 15);
        Product p4 = new SimpleProduct(UUID.randomUUID(), "Meat", 460);
        Product p5 = new SimpleProduct(UUID.randomUUID(), "Oil", 22);

        productsMap.put(p1.getId(), p1);
        productsMap.put(p2.getId(), p2);
        productsMap.put(p3.getId(), p3);
        productsMap.put(p4.getId(), p4);
        productsMap.put(p5.getId(), p5);

        return productsMap;
    }

    private Map<UUID, Article> buildArticlesMap() {
        Map<UUID, Article> articles = new HashMap<>();

        Article a1 = new Article(UUID.randomUUID(), "Top 3 products for fast lunch", "Here are the top 3 products for a quick lunch...");
        Article a2 = new Article(UUID.randomUUID(), "Healthy snacks for the office", "Looking for healthy office snacks? Here's a list...");
        Article a3 = new Article(UUID.randomUUID(), "How to make a perfect smoothie", "Follow these steps for the perfect smoothie...");
        Article a4 = new Article(UUID.randomUUID(), "10-minute breakfast ideas", "Start your day with these quick breakfast recipes...");
        Article a5 = new Article(UUID.randomUUID(), "Quick and easy dinner ideas", "Make dinner in less than 30 minutes with these simple recipes...");

        articles.put(a1.getId(), a1);
        articles.put(a2.getId(), a2);
        articles.put(a3.getId(), a3);
        articles.put(a4.getId(), a4);
        articles.put(a5.getId(), a5);

        return articles;
    }
}
