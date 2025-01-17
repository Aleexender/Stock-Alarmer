package org.example.stockAlarmer.module.stock.presentation;

import lombok.RequiredArgsConstructor;
import org.example.stockAlarmer.global.common.ApiResponse;
import org.example.stockAlarmer.module.stock.application.StockService;
import org.example.stockAlarmer.module.stock.domain.Stock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {
    private final StockService stockService;

    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse<List<Stock>> stocks(){
        var result = stockService.getAllInfo();
        return ApiResponse.ok(result);
    }

}
