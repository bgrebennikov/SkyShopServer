package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class StorageServiceTest {

    private StorageService storageService;

    @BeforeEach
    void setUp() {
        storageService = new StorageService();
    }

    @Test
    @DisplayName("Должен возвращать все товары и статьи в одном списке")
    void shouldReturnAllSearchableItems() {

        List<Searchable> items = storageService.getSearchableItems();

        assertThat(items).hasSize(10);

        long productCount = items.stream().filter(i -> i instanceof Product).count();
        long articleCount = items.stream().filter(i -> i instanceof Article).count();

        assertThat(productCount).isEqualTo(5);
        assertThat(articleCount).isEqualTo(5);
    }

    @Test
    @DisplayName("Должен находить существующий продукт по его ID")
    void shouldFindProductByExistingId() {

        UUID existingId = storageService.getProducts().keySet().iterator().next();
        Product expectedProduct = storageService.getProducts().get(existingId);

        Optional<Product> result = storageService.getProductById(existingId);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(expectedProduct);
        assertThat(result.get().getTitle()).isNotNull();
    }

    @Test
    @DisplayName("Должен возвращать пустой Optional, если продукт с таким ID не найден")
    void shouldReturnEmptyOptionalWhenIdDoesNotExist() {

        UUID nonExistentId = UUID.randomUUID();
        Optional<Product> result = storageService.getProductById(nonExistentId);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Должен содержать конкретные товары после инициализации")
    void shouldContainSpecificProductsAfterInitialization() {

        Collection<Product> products = storageService.getProducts().values();

        assertThat(products).extracting(Product::getTitle)
                .containsExactlyInAnyOrder("Milk", "Coconut", "Banana", "Meat", "Oil");
    }

    @Test
    @DisplayName("Должен содержать корректное количество статей")
    void shouldContainCorrectArticles() {
        Map<UUID, Article> articles = storageService.getArticles();

        assertThat(articles).hasSize(5);

        articles.values().forEach(article -> {
            assertThat(article.getTitle()).isNotEmpty();
            assertThat(article.getSearchTerm()).containsIgnoringCase(article.getTitle());
        });
    }

}