package org.example.stockAlarmer.module.stock.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.stockAlarmer.module.stock.domain.Stock;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {
    private final StockApi stockApi;

    public Flux<Stock> getAllInfo() {
        return stockApi.fetchInfo()
                .doOnNext(Stock::validate)
                .doOnError(error -> log.error("Error while processing stocks", error))
                .onErrorResume(error -> Flux.empty())
                .timeout(Duration.ofSeconds(5));
    }
}
