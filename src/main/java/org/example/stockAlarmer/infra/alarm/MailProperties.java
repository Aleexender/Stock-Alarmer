package org.example.stockAlarmer.infra.alarm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.mail")
 class MailProperties {
    private final String host;
    private final String username;
    private final String password;
    private final String port;
}
