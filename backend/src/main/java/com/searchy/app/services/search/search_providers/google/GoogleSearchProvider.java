package com.searchy.app.services.search.search_providers.google;

import com.searchy.app.services.search.models.SearchEngineResult;
import com.searchy.app.services.search.models.SingleQueryResult;
import com.searchy.app.services.search.search_providers.google.response.GoogleCustomSearchResponse;
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
public class GoogleSearchProvider {

    private final WebClient webClient;

    @Autowired
    public GoogleSearchProvider(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<SearchEngineResult> search(List<String> queries) {
        return Flux.fromIterable(queries)
            .flatMap(this::makeRequest)
            .collectList()
            .map(queryResults -> new SearchEngineResult("Google", queryResults));
    }

    private Mono<SingleQueryResult> makeRequest(String query) {
        // TODO: Figure out why I can't read from application.properties. Community edition issues?
        String apiKey = "API_KEY";
        String searchEngineId = "SEARCH_ENGINE_ID";
        String url = "https://www.googleapis.com/customsearch/v1?key=" + apiKey + "&cx=" + searchEngineId + "&q=";
        URI uri = URI.create(url + URLEncoder.encode(query, StandardCharsets.UTF_8));

        return webClient.get()
            .uri(uri)
            .retrieve()
            .bodyToMono(GoogleCustomSearchResponse.class)
            .map(response -> new SingleQueryResult(query, response.searchInformation().totalResults(), null))
            .onErrorResume(error -> Mono.just(new SingleQueryResult(query, 0, error.getMessage())));
    }
}
