package org.smartcookie.repository;

import org.smartcookie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);

    User findUserByEmail(String name);

}
