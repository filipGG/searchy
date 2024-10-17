package com.searchy.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
            .exchangeStrategies(
                ExchangeStrategies.builder()
                .codecs(consumer -> consumer.defaultCodecs().maxInMemorySize(20 * 1024 * 1024))
                .build()
            )
            .build();
    }
}
