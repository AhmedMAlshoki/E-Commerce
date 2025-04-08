package com.example.ECommerce.SecurityConfig;


import com.example.ECommerce.SecurityConfig.CustomFilters.UsernamePasswordJsonFilter;
import com.example.ECommerce.SecurityConfig.JWT.AuthEntryPointJwt;
import com.example.ECommerce.SecurityConfig.JWT.AuthTokenFilter;
import com.example.ECommerce.SecurityConfig.JWT.JwtUtils;
import com.example.ECommerce.SecurityConfig.SecurityServices.UserDetailsServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsServiceImp userDetailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Autowired
    private JwtUtils jwtUtils;

    //Authentication Manager / Authentication Provider
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    //---------------------------------------------------
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //Roles Hierarchy Configuration
    @Bean
    public RoleHierarchy roleHierarchy() {

        return RoleHierarchyImpl.withDefaultRolePrefix()
                .role("ROLE_ADMIN").implies("ROLE_SUPPORT")
                .role("ROLE_SELLER").implies("ROLE_CUSTOMER")
                .build();
    }

    @Bean
    public UsernamePasswordJsonFilter usernamePasswordJsonFilter(AuthenticationManager authenticationManager) throws Exception {
       UsernamePasswordJsonFilter filter = new UsernamePasswordJsonFilter(authenticationManager);
        filter.setAuthenticationSuccessHandler(this::onAuthenticationSuccess);
        return filter;
    }
    //allow  to apply security rules based on the current user's roles, permissions, and other attributes.
    @Bean
    public DefaultMethodSecurityExpressionHandler expressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }


    //------------------------------------------------


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->{
                                                                              auth.requestMatchers("/api/v1/login").permitAll();
                                                                              auth.requestMatchers("/api/v1/hello").permitAll();
                                                                              auth.requestMatchers("/api/v1/register").permitAll();
                                                                              auth.requestMatchers("/api/v1/products").permitAll();
                                                                              auth.anyRequest().authenticated();
                }
                );
        http.addFilterAt(usernamePasswordJsonFilter(
                                                    authenticationManager(
                                                            http.getSharedObject(AuthenticationConfiguration.class))),
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        String jwt = jwtUtils.generateJwtToken(authentication);
        response.getWriter().write("{\"token\": \"" + jwt + "\"}");
        response.addHeader("Authorization", "Bearer " + jwt);
    }
}
