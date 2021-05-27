package org.smartcookie.service;

import org.smartcookie.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    boolean createUser(User user);

    boolean updateUser(User user);

    List<User> getAllUsers();

    Page<User> findUsersPaginated(Pageable pageable);

    User getUserById(Long id);

    boolean deleteUserById(Long id);
}
