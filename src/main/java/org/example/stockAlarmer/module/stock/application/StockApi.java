package org.example.stockAlarmer.module.stock.application;

import org.example.stockAlarmer.module.stock.dto.StockDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StockApi {
    Flux<StockDto.ListResponse> fetchInfo();

    Mono<StockDto.DetailResponse> getDetails(String symbol);
}
