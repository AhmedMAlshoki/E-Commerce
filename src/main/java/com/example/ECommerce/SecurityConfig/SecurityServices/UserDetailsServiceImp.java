package com.example.ECommerce.SecurityConfig.SecurityServices;

import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
             user = (User)userRepository.findByUsername(username).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return UserDetailsImp.build(user);
    }
}
