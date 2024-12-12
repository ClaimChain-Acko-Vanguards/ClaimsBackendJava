package com.acko.ClaimChain.ClaimChain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@Table(name = "USER_TABLE")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String role; // e.g., ROLE_USER or ROLE_ADMIN


    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return roles as authorities
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role));
    }

    // Getters and setters
}
