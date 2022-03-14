package com.example;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@EnableFeignClients
@SpringBootApplication
public class JapringApplication {

    public static void main(String[] args) {
        SpringApplication.run(JapringApplication.class, args);
    }

}
