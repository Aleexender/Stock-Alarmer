package org.example.stock_alarmer.infra.stock.web;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(AlphaVantageProperties.class)
 class AlphaVantageWebClient{

    protected AlphaVantageWebClient() {

    }

    @Bean
    public WebClient createWebClient( AlphaVantageProperties config) {
        return WebClient.builder()
                .baseUrl(config.getBaseurl())
                .build();
    }
}
