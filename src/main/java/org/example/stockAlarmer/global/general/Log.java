package org.example.stockAlarmer.global.general;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Log extends BaseEntity{ // todo local cache 사용하기 (caffeine cache)

    private String userId;

    private String message;

    private String exception;
}
