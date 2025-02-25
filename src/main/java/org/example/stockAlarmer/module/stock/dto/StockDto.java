package org.example.stockAlarmer.module.stock.dto;


import java.time.LocalDate;

public sealed interface StockDto permits StockDto.StocksResponse, StockDto.DetailResponse, StockDto.ListResponse {


    record StocksResponse(
            String symbol,
            String name,
            double price,
            LocalDate dividendDate,
            double dividendPrice
    ) implements StockDto {

    }

    record DetailResponse(
            String symbol,
            String open,
            String high,
            String low,
            String price,
            String volume,
            String latestTradingDay,
            String previousClose,
            String change,
            String changePercent
    ) implements StockDto {
    }

    record ListResponse(
            String symbol,
            String name,
            String exchange,
            String assetType,
            LocalDate ipoDate,
            LocalDate delistingDate,
            String status
    ) implements StockDto {
    }

}



