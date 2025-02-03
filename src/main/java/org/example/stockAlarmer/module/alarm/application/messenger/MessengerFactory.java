package org.example.stockAlarmer.module.alarm.application.messenger;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MessengerFactory {
    private Map<String, Messenger> messengers;

    public Messenger create(String type) {
        return switch (type) {
            case "Mail" -> messengers.get("Mail");
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
