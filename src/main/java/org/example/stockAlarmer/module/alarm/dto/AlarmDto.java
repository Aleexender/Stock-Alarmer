package org.example.stockAlarmer.module.alarm.dto;

import org.jetbrains.annotations.NotNull;

import static org.example.stockAlarmer.module.alarm.dto.AlarmDto.SubscribeDto;
public sealed interface AlarmDto permits SubscribeDto {
    record SubscribeDto(
            String name,
            @NotNull
            String symbol,
            @NotNull
            String email,
            @NotNull
            Double price,
            String messengerType
    ) implements AlarmDto {

    }
}