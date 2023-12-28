package com.example.pbl04;

import com.example.pbl04.config.PasswordMigration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;


@EntityScan(basePackages = "com.example.pbl04.entity")
@SpringBootApplication
public class Pbl04Application {

    public static void main(String[] args) {
        SpringApplication.run(Pbl04Application.class, args);
    }
//    @Bean
//    public CommandLineRunner runMigration(PasswordMigration passwordMigration) {
//        return args -> passwordMigration.tranPassword();
//    }
}
