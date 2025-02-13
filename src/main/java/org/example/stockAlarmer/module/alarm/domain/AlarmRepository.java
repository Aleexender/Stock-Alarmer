package org.example.stockAlarmer.module.alarm.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm,Long> {
    Optional<Alarm> findByEmailAndSymbol(String email, String symbol);

}
