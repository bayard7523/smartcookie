package org.smartcookie.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartcookie.model.User;
import org.smartcookie.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
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
        logger.info("User email: " + user.getEmail() + " Roles: " + user.getRole().getName());
        return CustomUserDetails.build(user);
    }
}