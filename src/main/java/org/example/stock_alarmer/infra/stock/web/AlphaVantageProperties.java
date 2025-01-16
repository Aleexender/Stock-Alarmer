package org.example.stock_alarmer.infra.stock.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "alphavantage")
class AlphaVantageProperties {
    private final String apiKey;
    private final String baseurl;
}
