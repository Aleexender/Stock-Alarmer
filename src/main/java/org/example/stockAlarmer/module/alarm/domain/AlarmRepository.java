package org.example.stockAlarmer.module.alarm.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm,Long> {
    boolean existsByName(String name);

    Optional<Alarm> findByName(String name);
}
