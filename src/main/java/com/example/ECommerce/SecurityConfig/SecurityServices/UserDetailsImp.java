package com.example.ECommerce.SecurityConfig.SecurityServices;

import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsImp implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;


    @Getter
    private Long id;
    private String username;
    private Collection<? extends GrantedAuthority> role;
    @JsonIgnore
    private String password; //Should use PasswordEncoder here or At UserDetailImp

    private Roles simpleRole;
    public UserDetailsImp(){}
    public UserDetailsImp(Long id,String username,String password, Roles role) {
        this.username = username;
        this.password = password;
        this.role = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
        this.simpleRole = role;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the role into a GrantedAuthority with a ROLE_ prefix
        return role;
    }

    public static UserDetailsImp build(User user) {

        return new UserDetailsImp (
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole());
    }


    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }

    public Roles getRole() {
        return this.simpleRole;
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

    public User getUser(){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(simpleRole);
        return user;
    }
}
