package org.example.stock_alarmer.infra;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "alphavantage")
public class AlphaVantageConfig {
    private final String apiKey;
    private final String baseurl;

    public AlphaVantageConfig(String apiKey, String baseurl) {
        this.apiKey = apiKey;
        this.baseurl = baseurl;
    }
}
