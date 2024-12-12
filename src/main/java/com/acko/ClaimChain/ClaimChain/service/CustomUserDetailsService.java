package com.acko.ClaimChain.ClaimChain.service;


import com.acko.ClaimChain.ClaimChain.models.User;
import com.acko.ClaimChain.ClaimChain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Return the user as a Spring Security UserDetails object
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
