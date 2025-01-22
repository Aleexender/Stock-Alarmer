package org.example.stockAlarmer.infra.stock;

import org.example.stockAlarmer.module.stock.domain.Stock;
import reactor.core.publisher.Flux;

public interface StockApi {

    Flux<Stock> fetchInfo();
}
