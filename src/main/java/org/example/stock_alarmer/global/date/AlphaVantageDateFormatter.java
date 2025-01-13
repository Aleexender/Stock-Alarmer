package org.example.stock_alarmer.global.date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Configuration
public class AlphaVantageDateFormatter extends DateFormatter {


    @Override
    protected DateTimeFormatter now() {
        return DateTimeFormatter.ISO_LOCAL_DATE;
    }

}
