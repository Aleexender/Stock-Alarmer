package org.example.stockAlarmer.module.alarm.presentation;

import lombok.RequiredArgsConstructor;
import org.example.stockAlarmer.module.alarm.application.AlarmService;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AlarmController {
    private final AlarmService alarmService;

//    @PostMapping
//    @ResponseStatus(CREATED)
//    public ApiResponse subscribeAlarm(@RequestBody AlarmDto.SubscribeDto request){
//        alarmService.subscribe(request);
//    }



}
