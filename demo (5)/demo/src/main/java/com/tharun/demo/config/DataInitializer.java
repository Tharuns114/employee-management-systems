package com.tharun.demo.config;

import com.tharun.demo.model.User;
import com.tharun.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UserRepository repo, PasswordEncoder encoder) {
        return args -> {

            if (repo.findByUsername("admin").isEmpty()) {
                User user = new User();
                user.setUsername("admin");
                user.setPassword(encoder.encode("admin"));
                repo.save(user);

                System.out.println("✅ ADMIN USER CREATED");
            }
        };
    }
}
