package org.example.stockAlarmer.module.stock.application;

import lombok.RequiredArgsConstructor;
import org.example.stockAlarmer.infra.stock.StockApi;
import org.example.stockAlarmer.module.stock.domain.Stock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockApi stockApi;

    @Transactional(readOnly = true)
    public List<Stock> getAllInfo() {
        return stockApi.fetchInfo();
    }
}
