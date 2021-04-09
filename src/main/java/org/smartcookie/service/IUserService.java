package org.smartcookie.service;

import org.smartcookie.model.User;

import java.util.List;

public interface IUserService {
    boolean createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    boolean deleteUserById(Long id);
}
