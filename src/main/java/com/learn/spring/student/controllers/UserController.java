package com.learn.spring.student.controllers;

import com.learn.spring.student.config.user.User;
import com.learn.spring.student.impl.UserServiceImpl;
import com.learn.spring.student.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(path="api/v1/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;

    }
    @GetMapping
    public List<User> getUsers(){
        return userServiceImpl.getAllUsers();
    }
    @PostMapping()
    public void createNewUser(@RequestBody User user){
        userServiceImpl.createUser(user);
    }
    @DeleteMapping(path="/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userServiceImpl.deleteUser(userId);
    }
    @PutMapping(path="/{userId}")
    public void updateUser(@PathVariable("userId") Long userId,@RequestParam(required = false) String userName,@RequestParam(required = false) String fullName,@RequestParam(required = false) String email, @RequestParam(required = false) String role){
            userServiceImpl.updateUser(userId, userName,fullName, email, role);
    }

}
