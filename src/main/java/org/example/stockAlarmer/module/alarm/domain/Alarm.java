package org.example.stockAlarmer.module.alarm.domain;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.stockAlarmer.global.general.BaseEntity;

import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends BaseEntity {

    private String name;

    private String symbol;

    private Double price;

    private LocalDate createdAt = LocalDate.now();

    private LocalDate updatedAt;

    public Alarm(String name, String symbol, Double price) {
        this(name, symbol, price, LocalDate.now(), null);
    }

    public static Alarm create(String name, String symbol, Double price) {
        return new Alarm( name, symbol, price, LocalDate.now(), null);
    }

    public void updatePrice(Double newPrice) {
        this.price = newPrice;
        this.updatedAt = LocalDate.now();
    }

    public void updateSymbol(String newSymbol) {
        this.symbol = newSymbol;
        this.updatedAt = LocalDate.now();
    }

}
