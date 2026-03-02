package org.skypro.skyshop.model.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.ContentType;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String title;
    private final UUID id;

    public Product(UUID id, String title) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or blank");
        }

        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public abstract double getPrice();

    public abstract boolean isSpecial();

    @Override
    public String toString() {
        return "%s: %s%n".formatted(title, getPrice());
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return title;
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return ContentType.PRODUCT.name();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Product product = (Product) obj;
        return Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
