package org.smartcookie.service;

import org.smartcookie.model.Role;
import org.smartcookie.model.User;
import org.smartcookie.repository.IRoleRepository;
import org.smartcookie.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository iRoleRepository;

    public UserService(IUserRepository iUserRepository, PasswordEncoder passwordEncoder, IRoleRepository iRoleRepository) {
        this.iUserRepository = iUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.iRoleRepository = iRoleRepository;
    }

    @Override
    public boolean createUser(User user) {
        User userDb = iUserRepository.findUserByEmail(user.getEmail());
        if (userDb != null) {
            return false;
        }

        User u = new User();
        u.setFirstName(user.getFirstName());
        u.setSecondName(user.getSecondName());
        u.setEmail(user.getEmail());
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role=iRoleRepository.findRoleByName("ROLE_STUDENT");
        u.setRole(role);
        iUserRepository.save(u);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(iUserRepository.findAll());
    }

    @Override
    public User getUserById(Long id) {
        return iUserRepository.findUserById(id);
    }

    @Override
    public boolean deleteUserById(Long id) {
        if (iUserRepository.findById(id).isPresent()) {
            iUserRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
