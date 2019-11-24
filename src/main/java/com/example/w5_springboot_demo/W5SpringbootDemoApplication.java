package com.example.w5_springboot_demo;

import com.codegym.service.ClassroomService;
import com.codegym.service.StudentService;
import com.codegym.service.impl.ClassroomServiceImpl;
import com.codegym.service.impl.StudentServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.Entity;

@SpringBootApplication
@Configuration
@EnableWebMvc
@ComponentScan("com.codegym.controller")
@EntityScan("com.codegym.model")
@EnableTransactionManagement
@EnableJpaRepositories("com.codegym.repository")
@EnableSpringDataWebSupport
public class W5SpringbootDemoApplication {
    @Bean
    public StudentService studentService() {
        return new StudentServiceImpl();
    }

    @Bean
    public ClassroomService classroomService() {
        return new ClassroomServiceImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(W5SpringbootDemoApplication.class, args);
    }

}
