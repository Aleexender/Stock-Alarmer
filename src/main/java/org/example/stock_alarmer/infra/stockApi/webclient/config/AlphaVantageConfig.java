package org.example.stock_alarmer.infra.stockApi.webclient.config;

import lombok.Getter;
import org.example.stock_alarmer.global.date.DateFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "alphavantage")
 public class AlphaVantageConfig {
    private String apiKey;
    private String baseurl;
    private DateFormatter dateFormatter;

    public String getCurrentFormattedDate() {
        return dateFormatter.formatCurrentDate();
    }


}
