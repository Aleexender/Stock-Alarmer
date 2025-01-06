package org.example.stock_alarmer.infra.stockApi.webclient.config;

import lombok.Getter;
import org.example.stock_alarmer.global.date.DateFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "alphavantage")
 public class AlphaVantageConfig {
    @Value("${alphavantage.api-key}")
    private final String apiKey;
    @Value("${alphavantage.baseurl}")
    private final String baseurl;
    private final DateFormatter dateFormatter;

    protected AlphaVantageConfig(String apiKey, String baseurl, DateFormatter dateFormatter) {
        this.apiKey = apiKey;
        this.baseurl = baseurl;
        this.dateFormatter = dateFormatter;
    }

    public String getCurrentFormattedDate() {
        return dateFormatter.formatCurrentDate();
    }


}
