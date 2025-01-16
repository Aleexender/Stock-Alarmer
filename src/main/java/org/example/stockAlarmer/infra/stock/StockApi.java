package org.example.stockAlarmer.infra.stock;

import org.example.stockAlarmer.module.stock.domain.Stock;

import java.util.List;

public interface StockApi {

    List<Stock> fetchInfo();
}
