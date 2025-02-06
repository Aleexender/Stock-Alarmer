package org.example.stockAlarmer.event;

public record MailFailure(
        String userId,
        String message,
        String exception
) {
}
