package org.example.stock_alarmer.global.date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public abstract class DateFormatter {

    @Bean
    protected abstract DateTimeFormatter now();

    public String CurrentDate() {
        return LocalDate.now().format(now());
    }
    public String formatCustomDate(LocalDate date) {
        return date.format(now());
    }
}
