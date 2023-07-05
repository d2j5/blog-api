package com.example.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.blog.repository")
public class BlogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }

}

