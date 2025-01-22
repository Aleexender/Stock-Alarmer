package org.example.stockAlarmer.module.stock.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    private String symbol;

    private String name;

    private String exchange;

    private String assetType;

    private LocalDate ipoDate;

    private LocalDate delistingDate;

    private String status;


    public void validate() {
        // validation logic
    }

}

