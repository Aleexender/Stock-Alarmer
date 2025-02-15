package org.example.stockAlarmer.module.alarm.domain;

import jakarta.persistence.Column;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String symbol;

    @Column(nullable = true)
    private Double price;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    public Alarm(String name, String email, String symbol, Double price) {
        this(name, email, symbol, price, LocalDateTime.now(), null);
    }

    public static Alarm create(String name, String email, String symbol, Double price) {
        return new Alarm(name, email, symbol, price, LocalDateTime.now(), null);
    }

    public void adjust(Double price) {
        this.price = price;
        this.updatedAt = LocalDateTime.now();
    }

    public void modify(String symbol) {
        this.symbol = symbol;
        this.updatedAt = LocalDateTime.now();
    }

}
