package org.example.stock_alarmer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class StockAlarmerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockAlarmerApplication.class, args);
	}

}
