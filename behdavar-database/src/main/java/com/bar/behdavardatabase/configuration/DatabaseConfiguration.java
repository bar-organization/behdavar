package com.bar.behdavardatabase.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
public class DatabaseConfiguration {

    // TODO must use spring security to find user name
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of(System.getProperty("user.name"));
    }
}
