package org.example.stock_alarmer.infra.stockApi;

import lombok.RequiredArgsConstructor;
import org.example.stock_alarmer.infra.stockApi.webclient.config.AlphaVantageConfig;
import org.example.stock_alarmer.module.stock.domain.Stock;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AlphaVantageStockApi implements StockApi {
    private final WebClient webClient;
    private final AlphaVantageConfig alphaVantageConfig;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<Stock> fetchStocks() {
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/query")
                        .queryParam("function", "LISTING_STATUS")
                        .queryParam("apikey", alphaVantageConfig.getApiKey())
                        .queryParam("state", "active")
                        .queryParam("date", alphaVantageConfig.getCurrentFormattedDate())
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return parseResponse(response);
    }


    private List<Stock> parseResponse(String csvResponse) {
//        List<Stock> stocks = new ArrayList<>();
//        try (Reader reader = new StringReader(csvResponse)) {
//            CsvSchema schema = CsvSchema.emptySchema().withHeader();
//            CsvMapper mapper = new CsvMapper();
//            MappingIterator<Map<String, String>> iterator = mapper.readerFor(Map.class).with(schema).readValues(reader);
//
//            while (iterator.hasNext()) {
//                Map<String, String> row = iterator.next();
//                String delistingDate = row.get("delistingDate");
//                stocks.add(new Stock(
//                        row.get("symbol"),
//                        row.get("name"),
//                        row.get("exchange"),
//                        row.get("assetType"),
//                        LocalDate.parse(row.get("ipoDate"), DATE_FORMATTER),
//                        (delistingDate == null || delistingDate.isEmpty()) ? null : LocalDate.parse(delistingDate, DATE_FORMATTER),
//                        row.get("status")
//                ));
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to parse CSV response", e);
//        }
//        return stocks;
    }

}
