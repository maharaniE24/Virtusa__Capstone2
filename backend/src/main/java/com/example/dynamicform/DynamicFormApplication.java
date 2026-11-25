package com.example.dynamicform;

import com.example.dynamicform.model.AppUser;
import com.example.dynamicform.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DynamicFormApplication {
    public static void main(String[] args) { SpringApplication.run(DynamicFormApplication.class, args); }

    @Bean
    CommandLineRunner seedUsers(UserRepository users) {
        return args -> {
            if (users.findByEmail("admin@example.com").isEmpty())
                users.save(new AppUser("Admin User", "admin@example.com", "Admin@123", "ADMIN"));
            if (users.findByEmail("user@example.com").isEmpty())
                users.save(new AppUser("Demo User", "user@example.com", "User@123", "USER"));
        };
    }

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins("http://localhost:5173")
                    .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                    .allowedHeaders("*");
            }
        };
    }
}
