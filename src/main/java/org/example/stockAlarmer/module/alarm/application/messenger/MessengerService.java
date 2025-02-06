package org.example.stockAlarmer.module.alarm.application.messenger;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service()
@RequiredArgsConstructor
public class MessengerService {
    private final MessengerFactory messengerFactory;

    public void send(String type, String message) {
        var messenger = messengerFactory.create(type);
        messenger.send(message, "receiver", "subject"); //todo fix the parms
    }


}
