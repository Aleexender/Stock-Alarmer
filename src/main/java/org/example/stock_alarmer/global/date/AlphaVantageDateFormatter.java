package org.example.stock_alarmer.global.date;

import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class AlphaVantageDateFormatter extends DateFormatter {

    @Override
    protected DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

}
