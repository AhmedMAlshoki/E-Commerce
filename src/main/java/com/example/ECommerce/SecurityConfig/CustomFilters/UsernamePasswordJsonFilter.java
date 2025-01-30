package com.example.ECommerce.SecurityConfig.CustomFilters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

public class UsernamePasswordJsonFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper= new ObjectMapper();

    public UsernamePasswordJsonFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        setFilterProcessesUrl("/api/v1/login");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException
    {
        try {
            // Parse JSON from request body
            Map<String, String> credentials = objectMapper.readValue(request.getInputStream(), Map.class);
            String username = credentials.get("username");
            String password = credentials.get("password");

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON login request", e);
        }
    }
}
