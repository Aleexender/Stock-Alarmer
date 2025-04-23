package org.example.stockAlarmer.module.stock.presentation;

import lombok.RequiredArgsConstructor;
import org.example.stockAlarmer.global.general.ApiResponse;
import org.example.stockAlarmer.module.stock.application.StockService;
import org.example.stockAlarmer.module.stock.dto.StockDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {
    private final StockService stockService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<ApiResponse<List<StockDto.ListResponse>>> stocks() {
        return stockService.getAllInfo()
                .collectList()
                .map(ApiResponse::ok);
    }
}
