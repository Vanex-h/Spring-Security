package com.learn.spring.student.controllers;

import com.learn.spring.student.config.JwtUtil;
import com.learn.spring.student.dao.UserDao;
import com.learn.spring.student.dto.AuthenticationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, UserDao userDao) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
    }



//    @GetMapping
//    public  String getUsers(){
//        return "HI";
//    }

    @GetMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request
    ) throws Exception {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

//        final UserDetails user= userDao.findUserByEmail(request.getEmail());
         return ResponseEntity.ok("hello");
//        System.out.println(user.getPassword());
//        if(user != null){
//            return ResponseEntity.ok(jwtUtil.generateToken(user));
//        }
//            return ResponseEntity.status(400).body("Some error has occurred");
    }

}
