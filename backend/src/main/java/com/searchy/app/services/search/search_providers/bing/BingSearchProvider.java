package com.searchy.app.services.search.search_providers.bing;

import com.searchy.app.services.search.models.SearchEngineResult;
import com.searchy.app.services.search.models.SingleQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class BingSearchProvider {
    private final WebClient webClient;
    private final BingResponseParser responseParser;

    @Autowired
    public BingSearchProvider(WebClient webClient, BingResponseParser responseParser) {
        this.webClient = webClient;
        this.responseParser = responseParser;
    }

    public Mono<SearchEngineResult> search(List<String> queries) {
        return Flux.fromIterable(queries)
            .flatMap(this::makeRequest)
            .collectList()
            .map(queryResults -> new SearchEngineResult("Bing", queryResults));
    }

    private Mono<SingleQueryResult> makeRequest(String query) {
        URI uri = URI.create("https://www.bing.com/search?q=" + URLEncoder.encode(query, StandardCharsets.UTF_8));

        return webClient
            .get()
            .uri(uri)
            // We try to look human
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
            .header("Accept-Language", "en")
            .retrieve()
            .bodyToMono(String.class)
            .map(this.responseParser::parse)
            .map(hits -> new SingleQueryResult(query, hits, null))
            .onErrorResume(error -> Mono.just(new SingleQueryResult(query, 0, error.getMessage())));
    }
}
