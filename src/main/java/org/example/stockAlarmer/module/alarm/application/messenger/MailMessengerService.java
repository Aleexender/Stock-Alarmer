package org.example.stockAlarmer.module.alarm.application.messenger;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@RequiredArgsConstructor
public final class MailMessengerService implements Messenger {
    private final MailProperties mailProperties;
    private final JavaMailSender javaMailSender;

    @Override
    public void send(String message) {
    }

    @Override
    public void sendMail(MailSpec spec) {
        if (!spec.isValid()) {
            throw new IllegalArgumentException("Invalid mail specification");
        }

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            helper.setTo(spec.to());
            helper.setSubject(spec.subject());
            helper.setText(spec.content());
            helper.setFrom(spec.from() != null ? spec.from() : mailProperties.getUsername());

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
