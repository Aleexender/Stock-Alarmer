package org.example.stockAlarmer.infra.stock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.stockAlarmer.global.date.DateFormatter;
import org.example.stockAlarmer.infra.stock.dto.StockInfraDto;
import org.example.stockAlarmer.module.stock.application.StockApi;
import org.example.stockAlarmer.module.stock.domain.Stock;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
class AlphaVantageStockApi implements StockApi {
    private final WebClient webClient;
    private final AlphaVantageProperties properties;
    private final DateFormatter dateFormatter;
    private final ResponseConverter responseConverter;

    @Override
    public Flux<Stock> fetchInfo() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/query")
                        .queryParam("function", "LISTING_STATUS")
                        .queryParam("apikey", properties.getApiKey())
                        .queryParam("state", "active")
                        .queryParam("date", dateFormatter.CurrentDate())
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(responseConverter::parse)
                .flatMapMany(Flux::fromIterable)
                .doOnNext(stock -> log.info("Stock processed: {}", stock));
    }

    @Override
    public Mono<StockInfraDto.StockDetailResponseWrapper> getDetails(String symbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/query")
                        .queryParam("function", "GLOBAL_QUOTE")
                        .queryParam("symbol", symbol)
                        .queryParam("apikey", properties.getApiKey())
                        .build())
                .retrieve()
                .bodyToMono(StockInfraDto.StockDetailResponseWrapper.class);
    }
}
