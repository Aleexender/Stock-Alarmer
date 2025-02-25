package org.example.stockAlarmer.infra.stock;

import lombok.RequiredArgsConstructor;
import org.example.stockAlarmer.infra.stock.dto.AlphaStockDto;
import org.example.stockAlarmer.module.stock.application.StockApi;
import org.example.stockAlarmer.module.stock.dto.StockDto;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
class AlphaStockAdapter implements StockApi {
    private final AlphaVantageStockApi stockApi;


    @Override
    public Flux<StockDto.ListResponse> fetchInfo() {
        return stockApi.fetchInfo();
    }

    @Override
    public Mono<StockDto.DetailResponse> getDetails(String symbol) {
        return stockApi.getDetails(symbol)
                .map(this::convertToStockDetailDto);
    }

    private StockDto.DetailResponse convertToStockDetailDto(AlphaStockDto.DetailResponseWrapper wrapper) {
        AlphaStockDto.DetailResponseWrapper.StockDetailResponse response = wrapper.stockDetailResponse();
        return new StockDto.DetailResponse(
                response.symbol(),
                response.open(),
                response.high(),
                response.low(),
                response.price(),
                response.volume(),
                response.latestTradingDay(),
                response.previousClose(),
                response.change(),
                response.changePercent()
        );
    }
}
