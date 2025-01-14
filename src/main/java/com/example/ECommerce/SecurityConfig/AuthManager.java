package com.example.ECommerce.SecurityConfig;

import com.example.ECommerce.SecurityConfig.SecurityExceptions.NoAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthManager implements AuthenticationManager {
    private List<AuthenticationProvider> providers = new ArrayList<>();
    public AuthManager() {

    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        for (AuthenticationProvider provider : providers) {
            try {
                return provider.authenticate(authentication);
            } catch (AuthenticationException e) {
                // Try the next provider
            }
        }
        throw new NoAuthenticationProvider("No authentication provider found");
    }
    public void addProvider(AuthenticationProvider provider) {
        providers.add(provider);
    }

    public void removeProvider(AuthenticationProvider provider) {
        providers.remove(provider);
    }
}
