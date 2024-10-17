package com.searchy.app.controllers;

import com.searchy.app.services.search.models.SearchResult;
import com.searchy.app.services.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public SearchResult search(@RequestParam(value = "query") String query) {
        if (query == null || query.isEmpty()) {
            return new SearchResult(List.of());
        }

        return searchService.performSearch(query);
    }
}
