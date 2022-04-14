package com.example.test;

import lombok.Builder;

@Builder
public record TestRecord(String name, String address) {

    public static void main(String[] args) {
        TestRecord result = TestRecord.builder().name("seokjae").address("seoul").build();
        System.out.println(result);
    }
}
