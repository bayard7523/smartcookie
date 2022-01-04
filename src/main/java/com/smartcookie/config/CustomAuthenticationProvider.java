package com.smartcookie.config;

import com.smartcookie.service.CustomUserDetails;
import com.smartcookie.service.CustomUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService customUserDetailsService;

    public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = (String) authentication.getCredentials();
        CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("username not found");
        }
        if (!encoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("password incorrect");
        }
        if (!userDetails.isAccountNonExpired()) {
            throw new CredentialsExpiredException("account expired");
        }
        if (!userDetails.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("password expired");
        }
        if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("account locked");
        }


        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
