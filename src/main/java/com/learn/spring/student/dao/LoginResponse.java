package com.learn.spring.student.dao;

import com.learn.spring.student.config.user.Role;
import com.learn.spring.student.config.user.User;

import java.util.Set;

public class LoginResponse {
    public String token;
    public User user;
    public Set<Role> roles;

    public LoginResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String rolesToString() {
        String rolesString = "";
        for (Role role : roles) {
            rolesString += role.getRole() + ", ";
        }
        return rolesString;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", user=" + user +
                ", roles=" + roles +
                '}';
    }
}
