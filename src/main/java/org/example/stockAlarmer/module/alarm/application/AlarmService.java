package org.example.stockAlarmer.module.alarm.application;

import lombok.RequiredArgsConstructor;
import org.example.stockAlarmer.module.alarm.domain.AlarmRepository;
import org.example.stockAlarmer.module.alarm.dto.AlarmDto;
import org.example.stockAlarmer.module.alarm.mapper.AlarmMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final ApplicationEventPublisher eventPublisher;

    public void subscribe(AlarmDto.SubscribeDto request) {
        var alarm = AlarmMapper.toDomain(request);
        alarmRepository.save(alarm);
//        eventPublisher.publishEvent();
    }
}
