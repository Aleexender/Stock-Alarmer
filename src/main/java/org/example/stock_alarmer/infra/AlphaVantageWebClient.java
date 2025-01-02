package org.example.stock_alarmer.infra;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(AlphaVantageConfig.class)
public class AlphaVantageWebClient{

    private AlphaVantageWebClient() {
        throw new AssertionError(); //todo : stock 에러 만들기
    }

    @Bean
    public static WebClient createWebClient( AlphaVantageConfig config) {
        return WebClient.builder()
                .baseUrl(config.getBaseurl())
                .defaultHeader("Content-Type", "application/json") // 기본 헤더
                .build();
    }
}
