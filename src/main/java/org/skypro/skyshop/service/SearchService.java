package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String searchQuery) {
        if (searchQuery == null || searchQuery.isBlank()) {
            return List.of();
        }

        String normalizedQuery = searchQuery.toLowerCase().trim();

        return storageService.getSearchableItems().stream()
                .filter(item -> {
                    String term = item.getSearchTerm();
                    return term != null &&
                            term.toLowerCase().contains(normalizedQuery);
                })
                .map(SearchResult::fromSearchable)
                .toList();
    }
}

