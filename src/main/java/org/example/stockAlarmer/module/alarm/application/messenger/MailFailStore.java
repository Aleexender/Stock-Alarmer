package org.example.stockAlarmer.module.alarm.application.messenger;

import lombok.RequiredArgsConstructor;
import org.example.stockAlarmer.event.MailFailure;
import org.example.stockAlarmer.global.cache.MailFailureWrapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class MailFailStore {
    private final AtomicLong failureIdGenerator = new AtomicLong(0);

    @CachePut(
            value = "mailFailures",
            key = "#result.failureId"
    )
    @TransactionalEventListener
    public MailFailureWrapper save(MailFailure mailFailure) {
        var failureId = failureIdGenerator.incrementAndGet();
        return new MailFailureWrapper(failureId, mailFailure, LocalDateTime.now());
    }

}
