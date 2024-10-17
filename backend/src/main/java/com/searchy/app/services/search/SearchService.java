package com.searchy.app.services.search;

import com.searchy.app.services.search.models.SearchResult;
import com.searchy.app.services.search.search_providers.bing.BingSearchProvider;
import com.searchy.app.services.search.search_providers.google.GoogleSearchProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SearchService {

    private final GoogleSearchProvider googleSearchProvider;
    private final BingSearchProvider bingSearchProvider;
    private final QueryParser queryParser;

    @Autowired
    public SearchService(
        GoogleSearchProvider googleSearchProvider,
        BingSearchProvider bingSearchProvider,
        QueryParser queryParser
    ) {
        this.googleSearchProvider = googleSearchProvider;
        this.bingSearchProvider = bingSearchProvider;
        this.queryParser = queryParser;
    }

    public SearchResult performSearch(String rawQuery) {
        List<String> individualQueries = queryParser.parse(rawQuery);

        var googleResult = googleSearchProvider.search(individualQueries);
        var bingResult = bingSearchProvider.search(individualQueries);

        var results = Mono.zip(googleResult, bingResult)
            .map(tuple -> List.of(tuple.getT1(), tuple.getT2()))
            .block();

        return new SearchResult(results);
    }
}
