package org.example.stockAlarmer.global.date;

import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class AlphaVantageDateFormatter extends DateFormatter {


    @Override
    protected DateTimeFormatter now() {
        return DateTimeFormatter.ISO_LOCAL_DATE;
    }

}
