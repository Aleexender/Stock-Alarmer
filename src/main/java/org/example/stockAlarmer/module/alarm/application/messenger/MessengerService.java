package org.example.stockAlarmer.module.alarm.application.messenger;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service()
@RequiredArgsConstructor
public class MessengerService {
    private final MessengerFactory messengerFactory;

    public void send(String type, String email, String message) {
        var messenger = messengerFactory.create(type);
        messenger.send(message, email, message);
    }


}
