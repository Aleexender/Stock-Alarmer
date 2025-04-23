package org.example.stockAlarmer.module.stock.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.stockAlarmer.module.stock.dto.StockDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {
    private final StockApi stockApi;

    public Flux<StockDto.ListResponse> getAllInfo() {
        return stockApi.fetchInfo()
                .doOnError(error -> log.error("Error while processing stocks", error))
                .onErrorResume(error -> Flux.empty())
                .timeout(Duration.ofSeconds(5));
    }

    public Mono<StockDto.DetailResponse> getDetails(String symbol) {
        return stockApi.getDetails(symbol)
                .doOnError(error -> log.error("Error while processing stocks", error))
                .onErrorResume(error -> Mono.empty())
                .timeout(Duration.ofSeconds(5));
    }
}
