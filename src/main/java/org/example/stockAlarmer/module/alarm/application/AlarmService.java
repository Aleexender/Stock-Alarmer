package org.example.stockAlarmer.module.alarm.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.stockAlarmer.module.alarm.application.messenger.MessengerService;
import org.example.stockAlarmer.module.alarm.domain.AlarmRepository;
import org.example.stockAlarmer.module.alarm.dto.AlarmDto;
import org.example.stockAlarmer.module.alarm.mapper.AlarmMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final MessengerService messengerService;

    @Transactional
    public void subscribe(AlarmDto.SubscribeDto request) {
        var alarm = AlarmMapper.toDomain(request);

        alarmRepository.findByEmailAndSymbol(request.email(), request.symbol())
                .ifPresentOrElse(
                        existingAlarm -> existingAlarm.updateThreshold(request.price()),
                        () -> alarmRepository.save(alarm)
                );
        messengerService.send(request.messengerType(),request.email(),"Dear " +  request.name() + " " + request.name()+" is subscribed");
    }
}
