package com.learn.spring.student.services;

import com.learn.spring.student.config.user.User;
import com.learn.spring.student.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(Long id, String username, String fullName, String email, String role);
    void deleteUser(Long id);

    
}
