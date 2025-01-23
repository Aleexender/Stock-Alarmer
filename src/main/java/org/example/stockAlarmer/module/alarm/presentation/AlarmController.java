package org.example.stockAlarmer.module.alarm.presentation;

import lombok.RequiredArgsConstructor;
import org.example.stockAlarmer.global.common.ApiResponse;
import org.example.stockAlarmer.module.alarm.application.AlarmService;
import org.example.stockAlarmer.module.alarm.dto.AlarmDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequiredArgsConstructor
public class AlarmController {
    private final AlarmService alarmService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ApiResponse subscribeAlarm(@RequestBody AlarmDto.SubscribeDto request){
        alarmService.subscribe(request);
    }



}
