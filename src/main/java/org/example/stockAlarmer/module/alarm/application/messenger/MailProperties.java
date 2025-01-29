package org.example.stockAlarmer.module.alarm.application.messenger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "mail")
 class MailProperties {
    private final String host;
    private final String username;
    private final String password;
    private final String port;
}
