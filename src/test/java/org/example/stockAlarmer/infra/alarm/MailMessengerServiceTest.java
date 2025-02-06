package org.example.stockAlarmer.infra.alarm;

import jakarta.mail.internet.MimeMessage;
import org.assertj.core.api.BDDAssertions;
import org.example.stockAlarmer.event.MailFailure;
import org.example.stockAlarmer.module.alarm.application.messenger.MailFailStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

@SpringBootTest
@RecordApplicationEvents
class MailMessengerServiceTest {
    @Autowired
    private MailMessengerService mailMessenger;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private MailFailStore mailFailStore;

    @MockitoBean
    private JavaMailSender javaMailSender;

    @MockitoBean
    private MailProperties mailProperties;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private ApplicationEvents events;

    @Test
    @DisplayName("메일 전송 실패시 캐시에 실패 정보가 저장되는지 확인")
    void whenMailSendingFails_thenFailureInfoShouldBeCached() {
        // Given
        String receiver = "test@example.com";
        String message = "Test Message";
        String subject = "Test Subject";
        String sender = "sender@example.com";

        BDDMockito.when(mailProperties.getUsername()).thenReturn(sender);

        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);  // MimeMessage도 mock 생성
        BDDMockito.when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        BDDMockito.doThrow(new MailSendException("send failed"))
                .when(javaMailSender).send(Mockito.any(MimeMessage.class));

        //when
        mailMessenger.send(message, receiver, subject);

        Cache mailFailuresCache = cacheManager.getCache("mailFailures");
        Cache.ValueWrapper valueWrapper = mailFailuresCache.get(1L);


        // then
        Assertions.assertAll(
                () -> BDDAssertions.then(events.stream(MailFailure.class).count())
                        .as("예외가 발생하면 MailFailure 이벤트가 발행된다.")
                        .isEqualTo(1),

                () -> BDDAssertions.then(mailFailuresCache).isNotNull(),

                () -> BDDAssertions.then(valueWrapper).isNotNull()
        );
    }
}
