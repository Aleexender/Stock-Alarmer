package org.example.stockAlarmer.module.alarm.application;

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

    public void subscribe(AlarmDto.SubscribeDto request) {
        var alarm = AlarmMapper.toDomain(request);
        alarmRepository.save(alarm);
        messengerService.send(request.messengerType(),"Alarm subscribed"); // todo fix the parms
    }
}
