package com.example.ECommerce.Config;

import com.example.ECommerce.Entities.User;
import com.example.ECommerce.SecurityConfig.SecurityServices.UserDetailsImp;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class AuditorAwareCurrentUser implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(principal -> {
                    if (principal instanceof UserDetailsImp) {
                        return ((UserDetailsImp) principal).getUser();
                    }
                    return null; // Or throw an exception
                });
    }
}
