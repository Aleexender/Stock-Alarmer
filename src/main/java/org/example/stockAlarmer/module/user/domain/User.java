package org.example.stockAlarmer.module.user.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String username;

}
