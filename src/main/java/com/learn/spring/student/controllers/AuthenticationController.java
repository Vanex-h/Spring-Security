package com.learn.spring.student.controllers;

import com.learn.spring.student.config.JwtUtil;
import com.learn.spring.student.config.user.User;
import com.learn.spring.student.dao.LoginResponse;
import com.learn.spring.student.dao.UserDao;
import com.learn.spring.student.dto.AuthenticationRequest;
import com.learn.spring.student.exceptions.BadRequestException;
import com.learn.spring.student.repository.UserRepository;
import com.learn.spring.util.Hash;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
public class AuthenticationController {
    private final UserDao userDao;
    private JwtUtil jwtUtil;

    private final UserRepository userRepository;

    public AuthenticationController(UserDao userDao, UserRepository userRepository) {

        this.userDao = userDao;
        this.userRepository = userRepository;
    }




    @PostMapping("/authenticate")
    public LoginResponse authenticate(@RequestBody AuthenticationRequest request) {
// Return a successful response
        User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(() -> new BadRequestException("User with provided email not found"));
        if(Hash.isTheSame(request.getPassword() , user.getPassword())){
                String token = jwtUtil.createToken(user.getId(), request.getEmail() , user.getRole().getRole().toString());
                return new LoginResponse(token , user);

        }else{
            throw new BadRequestException("Incorrect Email or Password");
        }

    }

}
