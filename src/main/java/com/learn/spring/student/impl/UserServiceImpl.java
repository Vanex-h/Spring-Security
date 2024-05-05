package com.learn.spring.student.impl;

import com.learn.spring.student.config.user.Role;
import com.learn.spring.student.config.user.User;
import com.learn.spring.student.models.Student;
import com.learn.spring.student.repository.UserRepository;
import com.learn.spring.student.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserServiceImpl implements UserService{
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void createUser(User user) {
        Optional<User> userOptional= userRepository.findUserByEmail(user.getEmail());

        if(userOptional.isPresent()){
            throw new IllegalStateException("Email already exists");
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        System.out.println(user);
    }

    @Override
    @Transactional
    public void updateUser(Long id, String username, String fullName, String email, String role) {
        User user= userRepository.findById(id).orElseThrow(()-> new IllegalStateException(
                "User with id " + id+ "does not exist"
        ));
        if(username != null && username.length() >0 && !Objects.equals(user.getUserName(), username)){
            user.setUserName(username);
        }
        if(fullName !=null &fullName.length()>0 && !Objects.equals(user.getFullName(), fullName)){
            user.setFullName(fullName);
        }
        if(role !=null &fullName.length()>0 && !Objects.equals(user.getRole(), role)){
            user.setRole(Role.valueOf(role));
        }
        if(email !=null && email.length()>0&& !Objects.equals(user.getEmail(),email)){
            Optional<User> userOptional= userRepository.findUserByEmail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            user.setEmail(email);
        }
    }

    @Override
    public void deleteUser(Long id) {
        boolean exists= userRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("User with id" + id +" doesn't exist");

        }
        userRepository.deleteById(id);
    }


    public void flush() {

    }


    public void deleteAllInBatch(Iterable entities) {

    }

    public void deleteAllByIdInBatch(Iterable iterable) {

    }


    public void deleteAllInBatch() {

    }


    public Object getOne(Object o) {
        return null;
    }


    public Object getById(Object o) {
        return null;
    }




    public Object getReferenceById(Object o) {
        return null;
    }


    public List findAll(Example example, Sort sort) {
        return null;
    }


    public List findAll(Example example) {
        return null;
    }


    public List saveAllAndFlush(Iterable entities) {
        return null;
    }


    public Object saveAndFlush(Object entity) {
        return null;
    }


    public List saveAll(Iterable entities) {
        return null;
    }


    public Object save(Object entity) {
        return null;
    }


    public Optional findById(Object o) {
        return Optional.empty();
    }


    public boolean existsById(Object o) {
        return false;
    }


    public List findAll() {
        return null;
    }


    public List findAllById(Iterable iterable) {
        return null;
    }


    public long count() {
        return 0;
    }


    public void deleteById(Object o) {

    }


    public void delete(Object entity) {

    }


    public void deleteAllById(Iterable iterable) {

    }


    public void deleteAll(Iterable entities) {

    }


    public void deleteAll() {

    }


    public List findAll(Sort sort) {
        return null;
    }


    public Page findAll(Pageable pageable) {
        return null;
    }


    public Optional findOne(Example example) {
        return Optional.empty();
    }


    public Page findAll(Example example, Pageable pageable) {
        return null;
    }


    public long count(Example example) {
        return 0;
    }


    public boolean exists(Example example) {
        return false;
    }


    public Object findBy(Example example, Function queryFunction) {
        return null;
    }
}
