package org.skypro.skyshop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test
    @DisplayName("Должен находить элементы если поисковый запрос содержит название")
    void shouldReturnResultsWhenQueryMatches() {
        Searchable item = mock(Searchable.class);

        when(item.getSearchTerm()).thenReturn("Телевизор сасунг");
        when(item.getContentType()).thenReturn("PRODUCT");
        when(item.getId()).thenReturn(UUID.randomUUID());

        when(storageService.getSearchableItems()).thenReturn(List.of(item));

        List<SearchResult> results = searchService.search("телевизор");
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getName()).isEqualTo("Телевизор сасунг");
    }

    @Test
    @DisplayName("Должен возвращать пустой список, если в хранилище нет подходящих объектов")
    void shouldReturnEmptyListWhenNoMatchesFound() {
        Searchable item = mock(Searchable.class);

        when(item.getSearchTerm()).thenReturn("Телевизор");
        when(storageService.getSearchableItems()).thenReturn(Collections.emptyList());

        List<SearchResult> results = searchService.search("холодильник");

        assertThat(results)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Должен возвращать пустой список, если в хранилище есть товары, но ни один не подходит")
    void shouldReturnEmptyListWhenNoMatchesExist() {

        Searchable item1 = mock(Searchable.class);
        when(item1.getSearchTerm()).thenReturn("Ноутбук Apple MacBook");

        Searchable item2 = mock(Searchable.class);
        when(item2.getSearchTerm()).thenReturn("Смартфон iPhone 15");

        when(storageService.getSearchableItems()).thenReturn(List.of(item1, item2));

        List<SearchResult> results = searchService.search("пылесос");

        assertThat(results)
                .isNotNull()
                .isEmpty();
    }

}