package org.example.stockAlarmer.module.alarm.application.messenger;

public interface Messenger {
    void send(String message, String receiver, String subject);

}
