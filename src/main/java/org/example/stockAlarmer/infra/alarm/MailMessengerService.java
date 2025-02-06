package org.example.stockAlarmer.infra.alarm;


import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.stockAlarmer.event.MailFailure;
import org.example.stockAlarmer.module.alarm.application.messenger.Messenger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("Mail")
@RequiredArgsConstructor
 class MailMessengerService implements Messenger {
    private final MailProperties mailProperties;
    private final JavaMailSender javaMailSender;
    private final ApplicationEventPublisher eventPublisher;

    @Async
    @Override
    @Transactional
    public void send(String message, String receiver, String subject) {
        try {
            var mimeMessage = javaMailSender.createMimeMessage();

            var helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(mailProperties.getUsername());
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(message, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | MailException e) {
            eventPublisher.publishEvent(new MailFailure(receiver, message, e.getMessage()));
        }
    }
}
