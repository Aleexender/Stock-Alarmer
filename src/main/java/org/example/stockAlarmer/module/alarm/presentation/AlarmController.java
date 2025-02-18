package org.example.stockAlarmer.module.alarm.presentation;

import lombok.RequiredArgsConstructor;
import org.example.stockAlarmer.global.general.ApiResponse;
import org.example.stockAlarmer.module.alarm.application.AlarmService;
import org.example.stockAlarmer.module.alarm.dto.AlarmDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/alarms")
public class AlarmController {
    private final AlarmService alarmService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void subscribeAlarm(@RequestBody AlarmDto.SubscribeDto request){
        alarmService.subscribe(request);
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<AlarmDto.GetSubscribeDto>> getAlarms(@PathVariable String email){
        List<AlarmDto.GetSubscribeDto> alarms = alarmService.getAlarms(email);
        return ApiResponse.ok(alarms);
    }
}
