package org.example.stockAlarmer.infra.stock.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.stockAlarmer.global.date.DateFormatter;
import org.example.stockAlarmer.infra.stock.ResponseConverter;
import org.example.stockAlarmer.infra.stock.StockApi;
import org.example.stockAlarmer.module.stock.domain.Stock;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlphaVantageStockApi implements StockApi {
    private final WebClient webClient;
    private final AlphaVantageProperties alphaVantageConfig;
    private final DateFormatter dateFormatter;
    private final ResponseConverter responseConverter;

    @Override
    public List<Stock> fetchInfo() {
        try {
            CompletableFuture<List<Stock>> future = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/query")
                            .queryParam("function", "LISTING_STATUS")
                            .queryParam("apikey", alphaVantageConfig.getApiKey())
                            .queryParam("state", "active")
                            .queryParam("date", dateFormatter.CurrentDate())
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(responseConverter::parse)
                    .toFuture();

            return future.join();
        } catch (Exception e) {
            log.error("Failed to fetch stock info", e);
            return Collections.emptyList();
        }
    }
}
