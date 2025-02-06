package org.example.stockAlarmer.global.cache;

import org.example.stockAlarmer.event.MailFailure;

import java.io.Serializable;
import java.time.LocalDateTime;

public record MailFailureWrapper(
        long failureId,
        MailFailure failure,
        LocalDateTime timestamp
) implements Serializable {
    private static final long serialVersionUID = 1L;
}
