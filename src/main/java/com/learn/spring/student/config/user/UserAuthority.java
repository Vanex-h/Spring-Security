package com.learn.spring.student.config.user;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

public class UserAuthority implements GrantedAuthority {
    @Getter
    public UUID userId;
    public String authority;

    public UserAuthority(UUID userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
