package org.example.stockAlarmer.module.alarm.application.messenger;

public sealed interface Messenger permits MailMessengerService {
    void send(String message);

    void sendMail(MailSpec spec);

    sealed interface MessageSpec {
        Type getType();
    }

    record MailSpec(
            String to,
            String subject,
            String content,
            String from
    ) implements MessageSpec {
        @Override
        public Type getType() {
            return Type.MAIL;
        }
    }

    enum Type {
        MAIL, KAKAO
    }
}
