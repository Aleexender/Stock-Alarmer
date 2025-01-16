package org.example.stock_alarmer.infra.stock;

import org.example.stock_alarmer.module.stock.domain.Stock;

import java.util.List;

public interface StockApi {

    List<Stock> fetchInfo();
}
