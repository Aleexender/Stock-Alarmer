package org.example.stockAlarmer.module.alarm.application.messenger;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
 class MessengerService {
    private final MessengerFactory messengerFactory;
    private final ApplicationEventPublisher eventPublisher;

    public void send(String type, String message) {
        var messenger = messengerFactory.create(type);
        var spec = messengerFactory.createSpec(type, "to", "subject", message, "from");
        messenger.sendMail(spec);
    }


}
