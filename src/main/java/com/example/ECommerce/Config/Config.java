package com.example.ECommerce.Config;

import com.example.ECommerce.Entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class Config {
    @Bean
    public AuditorAware<User> auditorProvider() {
        return new AuditorAwareCurrentUser();
    }
}
