package com.learn.spring.student.config.user;
import com.learn.spring.student.config.user.UserSecurityDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.learn.spring.student.repository.UserRepository;

import java.util.Optional;

@Component
public class UserSecurityDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserSecurityDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        System.out.println("user" + userOptional.get().getUserName());
        if(userOptional.isPresent()){
            return new UserSecurityDetails(userOptional.get());
        }else{
            throw new UsernameNotFoundException(email + " was not found");
        }
    }
}