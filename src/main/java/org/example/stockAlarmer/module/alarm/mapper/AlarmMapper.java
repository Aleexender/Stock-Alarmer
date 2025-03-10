package org.example.stockAlarmer.module.alarm.mapper;

import org.example.stockAlarmer.module.alarm.domain.Alarm;
import org.example.stockAlarmer.module.alarm.dto.AlarmDto;

public interface AlarmMapper {

    static Alarm toDomain(AlarmDto.SubscribeDto dto) {
        return Alarm.create(dto.name(), dto.email(), dto.symbol(), dto.price());
    }

    static AlarmDto.GetSubscribeDto toGetSubscribeDto(Alarm alarm) {
        return new AlarmDto.GetSubscribeDto(alarm.getName(), alarm.getSymbol(), alarm.getEmail(), alarm.getPrice());
    }
}
