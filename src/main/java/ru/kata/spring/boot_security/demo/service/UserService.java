package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
@Component
public interface UserService extends UserDetailsService {
    void addUser(User user);
    User getUserById(Long id);
    void updateUserById(User user);
    void deleteUserById(Long id);
    List<User> getAllUsers();
    User getUserByName(String username);
}
