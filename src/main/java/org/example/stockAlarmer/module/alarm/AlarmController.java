package org.example.stockAlarmer.module.alarm;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AlarmController {

    @PostMapping
    public ResponseEntity<String> subscribeAlarm() {
        return null;

    }



}
