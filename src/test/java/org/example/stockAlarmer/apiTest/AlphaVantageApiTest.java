package org.example.stockAlarmer.apiTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class AlphaVantageApiTest {

    private final String BASE_URL = "https://www.alphavantage.co/query";

    private final WebClient webClient = WebClient.create(BASE_URL);

    @Test
    public void fetchStockPrice() {
        String symbol = "AAPL"; // Example stock symbol

        // API 호출
        Mono<String> responseMono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("function", "TIME_SERIES_INTRADAY")
                        .queryParam("symbol", symbol)
                        .queryParam("interval", "1min")
                        .queryParam("adjust", "true")
                        .queryParam("outputsize", "compact")
                        .queryParam("apikey", API_KEY)
                        .build())
                .retrieve()
                .bodyToMono(String.class);

        // 응답 처리
        String response = responseMono.block(); // Blocking for simplicity in testing
        Assertions.assertNotNull(response);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            System.out.println("Response: " + jsonNode.toPrettyString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fetchLatestStockPrice() {
        String symbol = "AAPL"; // Example stock symbol

        // API 호출
        Mono<String> responseMono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("function", "TIME_SERIES_INTRADAY")
                        .queryParam("symbol", symbol)
                        .queryParam("interval", "1min")
                        .queryParam("apikey", API_KEY)
                        .build())
                .retrieve()
                .bodyToMono(String.class);

        // 응답 처리
        String response = responseMono.block(); // Blocking for simplicity in testing

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);

            // "Meta Data"에서 최신 데이터 시간 가져오기
            String lastRefreshed = jsonNode
                    .get("Meta Data")
                    .get("3. Last Refreshed")
                    .asText();

            // 해당 시간의 주식 데이터 가져오기
            JsonNode latestPriceData = jsonNode
                    .get("Time Series (1min)")
                    .get(lastRefreshed);

            // 가격 정보 출력
            if (latestPriceData != null) {
                double open = latestPriceData.get("1. open").asDouble();
                double high = latestPriceData.get("2. high").asDouble();
                double low = latestPriceData.get("3. low").asDouble();
                double close = latestPriceData.get("4. close").asDouble();
                int volume = latestPriceData.get("5. volume").asInt();

                System.out.printf("Latest Price for %s at %s%n", symbol, lastRefreshed);
                System.out.printf("Open: %.2f, High: %.2f, Low: %.2f, Close: %.2f, Volume: %d%n",
                        open, high, low, close, volume);
            } else {
                System.out.println("No data available for the latest time.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}