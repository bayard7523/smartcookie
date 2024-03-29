package com.smartcookie.domain.service.impl;

import com.smartcookie.domain.service.IUserService;
import com.smartcookie.persistence.entity.Role;
import com.smartcookie.persistence.entity.User;
import com.smartcookie.persistence.repository.IRoleRepository;
import com.smartcookie.persistence.repository.IUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository iRoleRepository;
    private final CourseService courseService;

    public UserService(IUserRepository iUserRepository, PasswordEncoder passwordEncoder, IRoleRepository iRoleRepository, CourseService courseService) {
        this.iUserRepository = iUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.iRoleRepository = iRoleRepository;
        this.courseService = courseService;
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
        Role role;
        if (user.getRole() != null) {
            role = iRoleRepository.findRoleById(user.getRole().getId());
        } else {
            role = iRoleRepository.findRoleByName("ROLE_STUDENT");
        }
        u.setRole(role);
        iUserRepository.save(u);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        User userDb = iUserRepository.findUserByEmail(user.getEmail());
        if (userDb != null) {
            Role role = iRoleRepository.findRoleById(user.getRole().getId());
            userDb.setRole(role);
            userDb.setCourses(user.getCourses());
            iUserRepository.save(userDb);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = iUserRepository.findAll();
        users.sort(Comparator.comparingLong(User::getId));
        return users;
    }

    @Override
    public Page<User> findUsersPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<User> userList;
        if (getAllUsers().size() < startItem) {
            userList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, getAllUsers().size());
            userList = getAllUsers().subList(startItem, toIndex);
        }
        Page<User> userPage = new PageImpl<User>(userList, PageRequest.of(currentPage, pageSize), getAllUsers().size());
        return userPage;
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
