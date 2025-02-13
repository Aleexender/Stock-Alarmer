package org.example.stockAlarmer.module.alarm.domain;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.stockAlarmer.global.general.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends BaseEntity {

    private String name;

    private String symbol;

    private Double price;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    public Alarm(String name, String symbol, Double price) {
        this(name, symbol, price, LocalDateTime.now(), null);
    }

    public static Alarm create(String name, String symbol, Double price) {
        return new Alarm( name, symbol, price, LocalDateTime.now(), null);
    }

    public void updateThreshold(Double newThreshold) {
        this.price = newThreshold;
        this.updatedAt = LocalDateTime.now();
    }

    public void modifyCode(String newCode) {
        this.symbol = newCode;
        this.updatedAt = LocalDateTime.now();
    }

}
