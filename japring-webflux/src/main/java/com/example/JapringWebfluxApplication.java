package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class JapringWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(JapringWebfluxApplication.class, args);
    }

}
