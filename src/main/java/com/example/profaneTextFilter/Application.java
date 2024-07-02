package com.example.profaneTextFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


//@EnableScheduling
//@EnableJpaRepositories(basePackages = "com.example.profane_text_filter.repository")
//@SpringBootApplication(scanBasePackages = "com.example.profaneTextFilter")
//@EntityScan("com.example.profane_text_filter.model")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
