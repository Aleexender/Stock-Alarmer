package org.example.stockAlarmer.infra.stock;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.example.stockAlarmer.module.stock.domain.Stock;
import org.example.stockAlarmer.module.stock.domain.StockMapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class ResponseConverter {
    private final StockMapper stockMapper;

    public ResponseConverter(StockMapper stockMapper) {
        this.stockMapper = stockMapper;
    }

    public List<Stock> parse(String response) {
        try (var parser = CSVParser.parse(response, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            return parser.getRecords().stream()
                    .map(csvRecord -> stockMapper.toDomain(csvRecord.toMap()))
                    .toList();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse stock data", e);
        }
    }
}
