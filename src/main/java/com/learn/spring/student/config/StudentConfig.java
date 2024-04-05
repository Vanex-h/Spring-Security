package com.learn.spring.student.config;
import com.learn.spring.student.config.user.Role;
import com.learn.spring.student.config.user.User;
import com.learn.spring.student.config.user.UserRepository;
import com.learn.spring.student.models.Student;
import com.learn.spring.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository repository){
//        return args -> {
//            User user1 = new User(
//                    "Vanessa",
//                    "Vanessa Hirwa",
//                    "vanessahirwa5@gmail",
//                   "1234",
//                    Role.ADMIN
//            );
//            User user2 = new User(
//                    "marley",
//                    "Marley Bob",
//                    "marleybob@gmail",
//                    "1234",
//                    Role.USER
//            );
////            Student bob = new Student(
////                    "Bob",
////                    "bobtinka@gmail",
////                    LocalDate.of(1887, Month.SEPTEMBER, 18)
////            );
//            repository.saveAll(
//                    List.of(user1, user2)
//            );
//        };
//    }
}
