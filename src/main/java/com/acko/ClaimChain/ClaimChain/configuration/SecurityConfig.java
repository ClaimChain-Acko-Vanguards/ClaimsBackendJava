package com.acko.ClaimChain.ClaimChain.configuration;

import com.acko.ClaimChain.ClaimChain.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Update to new security configuration
                http
    .authorizeHttpRequests()
                .requestMatchers("/login", "/register", "/public/**").permitAll()  // Ensure login is accessible to all
                .requestMatchers("/v1/**").hasRole("USER")
                .requestMatchers("/v2/**").hasRole("INSURANCE_COMPANY")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")  // Make sure this points to the correct login page
                .defaultSuccessUrl("/welcome", true)  // After login, redirect to a valid URL
                .permitAll();

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }
}
