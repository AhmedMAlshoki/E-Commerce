package com.example.ECommerce.SecurityConfig;

import com.example.ECommerce.Enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserDetailsImp implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    @Getter
    private String email;
    private Roles role;
    @JsonIgnore
    private String password;

    public UserDetailsImp(String username, String password, Roles role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the role into a GrantedAuthority with a ROLE_ prefix
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
