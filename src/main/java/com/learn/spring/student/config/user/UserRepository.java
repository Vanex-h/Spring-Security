package com.learn.spring.student.config.user;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import com.learn.spring.student.config.user.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

}
