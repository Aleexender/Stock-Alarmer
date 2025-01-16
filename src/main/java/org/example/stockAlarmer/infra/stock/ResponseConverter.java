package org.example.stockAlarmer.infra.stock;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.example.stockAlarmer.module.stock.domain.Stock;
import org.example.stockAlarmer.module.stock.mapper.StockMapper;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
public class ResponseConverter {
    private final StockMapper stockMapper;

    public ResponseConverter(StockMapper stockMapper) {
        this.stockMapper = stockMapper;
    }

    public List<Stock> parse(String response) {
        try (CSVParser parser = CSVParser.parse(response, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            return parser.getRecords().stream()
                    .map(csvRecord -> {
                        Map<String, String> recordMap = new HashMap<>(csvRecord.toMap());
                        return stockMapper.toDomain(recordMap);
                    })
                    .toList();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse stock data", e);
        }
    }
}
