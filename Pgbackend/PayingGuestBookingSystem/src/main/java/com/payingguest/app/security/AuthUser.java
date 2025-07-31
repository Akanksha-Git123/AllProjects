package com.payingguest.app.security;

import com.payingguest.app.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Builder
@Getter
@AllArgsConstructor
public class AuthUser implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Ensures role prefix is compatible with Spring Security
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getUserType().name()));
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();  // Use passwordHash field
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Use email as username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // You can update this based on DB logic later
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Or use a flag like user.isLocked() if added
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Add logic if needed for password expiry
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();  // Reflects actual user status from DB
    }
}
