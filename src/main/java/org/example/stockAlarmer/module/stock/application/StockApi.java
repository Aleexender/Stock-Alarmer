package org.example.stockAlarmer.module.stock.application;

import org.example.stockAlarmer.module.stock.domain.Stock;
import reactor.core.publisher.Flux;

public interface StockApi {
    Flux<Stock> fetchInfo();
}
