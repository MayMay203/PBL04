package com.example.pbl04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan(basePackages = "com.example.pbl04.entity")
@SpringBootApplication
public class Pbl04Application {

    public static void main(String[] args) {
        SpringApplication.run(Pbl04Application.class, args);
    }
}
