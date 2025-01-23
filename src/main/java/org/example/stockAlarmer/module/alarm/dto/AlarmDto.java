package org.example.stockAlarmer.module.alarm.dto;

import static org.example.stockAlarmer.module.alarm.dto.AlarmDto.*;

public sealed interface AlarmDto permits SubscribeDto {
    record SubscribeDto(
            String name,
            String symbol,
            Double price
    ) implements AlarmDto {
        public SubscribeDto {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }
            if (symbol == null || symbol.isBlank()) {
                throw new IllegalArgumentException("Symbol cannot be null or empty");
            }
            if (price == null || price < 0) {
                throw new IllegalArgumentException("Price cannot be null or negative");
            }
        }
    }
}