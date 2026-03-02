package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class BlogController {

    private final StorageService storageService;

    public BlogController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/articles")
    public Map<UUID, Article> getProducts() {
        return storageService.getArticles();
    }

}
