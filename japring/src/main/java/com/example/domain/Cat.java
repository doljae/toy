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
public class Cat implements Pet{
    private Long id;
    private String name;
    private LocalDate birthday;
    private Color color;

    @Override
    public String type() {
        return "CAT";
    }

    @Override
    public void cry() {
        System.out.println("야옹");
    }
}
