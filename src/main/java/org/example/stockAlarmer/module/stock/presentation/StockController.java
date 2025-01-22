package org.example.stockAlarmer.module.stock.presentation;

import lombok.RequiredArgsConstructor;
import org.example.stockAlarmer.global.common.ApiResponse;
import org.example.stockAlarmer.module.stock.application.StockService;
import org.example.stockAlarmer.module.stock.domain.Stock;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {
    private final StockService stockService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<ApiResponse<List<Stock>>> stocks() {
        return stockService.getAllInfo()
                .collectList()
                .map(ApiResponse::ok);
    }
}
