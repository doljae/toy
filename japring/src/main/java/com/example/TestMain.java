package com.example;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TestMain {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Person {
        private String name;
        private int age;
    }

    public static void main(String[] args) {
        final List<String> temp = List.of("apple", "pear", "grapes");
        try {
            final List<String> a = temp.stream().filter(word -> StringUtils.hasText("a")).toList();
            a.add("applepear");
            System.out.println("a works!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            List<String> b = temp.stream().filter(word -> StringUtils.hasText("a")).collect(
                Collectors.toList());
            b.add("applepear");
            System.out.println("b works!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            final List<String> c = temp.stream().filter(word -> StringUtils.hasText("a")).collect(
                Collectors.toList());
            c.get(0).replace("a", "aaaaaa");
            System.out.println(c);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Person p1 = Person.builder().name("name1").age(20).build();
        final Person p2 = Person.builder().name("name2").age(30).build();
        final Person p3 = Person.builder().name("name3").age(40).build();

        final List<Person> temp2 = List.of(p1, p2, p3);
        final List<Person> people = temp2.stream().filter(person -> person.getAge() > 25).toList();
        people.get(0).setAge(5555);
        System.out.println(people);
    }
}
