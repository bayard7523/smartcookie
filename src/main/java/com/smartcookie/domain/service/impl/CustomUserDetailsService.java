package com.smartcookie.domain.service.impl;

import com.smartcookie.persistence.entity.User;
import com.smartcookie.persistence.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final IUserRepository iUserRepository;

    public CustomUserDetailsService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = iUserRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("The user does not exist");
        }
        logger.info("User email: " + user.getEmail() + " Role: " + user.getRole().getName());
        return CustomUserDetails.build(user);
    }
}
