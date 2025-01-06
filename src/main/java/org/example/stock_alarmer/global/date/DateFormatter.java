package org.example.stock_alarmer.global.date;

import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public abstract class DateFormatter {

    protected abstract DateTimeFormatter getFormatter();

    public String formatCurrentDate() {
        return LocalDate.now().format(getFormatter());
    }
    public String formatCustomDate(LocalDate date) {
        return date.format(getFormatter());
    }
}
