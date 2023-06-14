package com.demo.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApplicationProperties {
    @Value("${application.security.authentication.jwt.secret-key}")
    private String jwtSecretKey;
    @Value("${application.security.authentication.jwt.expire-time-in-minutes}")
    private Long tokenExpireTimeInMinutes;
}
