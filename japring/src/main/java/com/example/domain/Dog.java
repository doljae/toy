package com.example.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dog implements Pet{
    private Long id;
    private String name;
    private LocalDate birthday;
    private Color color;

    @Override
    public String type() {
        return "DOG";
    }

    @Override
    public void cry() {
        System.out.println("멍멍");
    }
}
