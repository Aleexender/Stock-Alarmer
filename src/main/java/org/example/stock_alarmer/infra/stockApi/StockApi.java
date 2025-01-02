package org.example.stock_alarmer.infra.stockApi;

import org.example.stock_alarmer.module.stock.domain.Stock;

import java.util.List;

public interface StockApi {

    List<Stock> fetchStocks();
}
