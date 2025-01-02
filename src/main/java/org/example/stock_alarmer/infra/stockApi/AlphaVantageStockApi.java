package org.example.stock_alarmer.infra.stockApi;

import lombok.RequiredArgsConstructor;
import org.example.stock_alarmer.infra.AlphaVantageConfig;
import org.example.stock_alarmer.module.stock.domain.Stock;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlphaVantageStockApi implements StockApi {
    private final WebClient webClient;
    private final AlphaVantageConfig alphaVantageConfig;

    @Override
    public List<Stock> fetchStocks() {
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/query")
                        .queryParam("function", "LISTING_STATUS")
                        .queryParam("apikey", alphaVantageConfig.getApiKey()) // API 키를 쿼리 매개변수로 추가
                        .queryParam("state", "active") // 추가 필터 예시
                        .queryParam("date", "2025-1-1") // 추가 필터 예시
                        .build())
                .retrieve()
                .bodyToMono(String.class) // 응답을 String으로 받음
                .block(); // 동기 호출

        // JSON 데이터를 Stock 객체로 변환하여 반환
        return parseResponse(response);
    }

}
