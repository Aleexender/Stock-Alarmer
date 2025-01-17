package org.example.stockAlarmer.infra.stock.web;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(AlphaVantageProperties.class)
 class AlphaVantageWebClient{
    private static final int MAX_BUFFER_SIZE = 10 * 1024 * 1024;

    protected AlphaVantageWebClient() {

    }

    @Bean
    public WebClient createWebClient( AlphaVantageProperties config) {
        return WebClient.builder()
                .baseUrl(config.getBaseurl())
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(MAX_BUFFER_SIZE))
                .build();
    }
}
