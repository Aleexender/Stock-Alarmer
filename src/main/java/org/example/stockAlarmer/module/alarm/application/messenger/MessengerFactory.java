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

    public Messenger.MailSpec createSpec(String type, String to, String subject, String content, String from) {
        return switch (type) {
            case "Mail" -> new Messenger.MailSpec(to, subject, content, from);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
