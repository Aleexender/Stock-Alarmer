package org.example.stockAlarmer.infra.stock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.stockAlarmer.global.date.DateFormatter;
import org.example.stockAlarmer.module.stock.application.StockApi;
import org.example.stockAlarmer.module.stock.domain.Stock;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
class AlphaVantageStockApi implements StockApi {
    private final WebClient webClient;
    private final AlphaVantageProperties alphaVantageConfig;
    private final DateFormatter dateFormatter;
    private final ResponseConverter responseConverter;

    @Override
    public Flux<Stock> fetchInfo() {
        return webClient.get()
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
                .flatMapMany(Flux::fromIterable)
                .doOnNext(stock -> log.info("Stock processed: {}", stock));
    }
}
