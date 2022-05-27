package io.alexdo.mixtech.api.infrastructure.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app-security")
@Getter
@Setter
public class AppSecurityConfiguration {
    private String tokenSecret;
    private String tokenExpirationMSec;
    private String frontendRedirectUri;
}
