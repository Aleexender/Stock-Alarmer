package org.example.stockAlarmer.module.stock.dto;


import java.time.LocalDate;

public record StocksResponse(
        String symbol,
        String name,
        double price,
        LocalDate dividendDate,
        double dividendPrice
){

}
