package com.example.ECommerce.SecurityConfig.SecurityExceptions;

import org.springframework.security.core.AuthenticationException;

public class NoAuthenticationProvider extends AuthenticationException {
    public NoAuthenticationProvider(String msg) {
        super(msg);
    }
}
