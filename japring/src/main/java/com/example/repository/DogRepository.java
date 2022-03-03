package com.example.repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.domain.Color;
import com.example.domain.Dog;

@Repository
public class DogRepository {

    public List<Dog> getDogList() {
        return dogList;
    }

    public Dog getDog(int id) {
        return dogList.get(id);
    }

    private static List<Dog> dogList = Arrays.asList(Dog.builder()
                                                        .id(1L)
                                                        .name("dog1")
                                                        .birthday(LocalDate.now())
                                                        .color(Color.RED).build(),
                                                     Dog.builder()
                                                        .id(2L)
                                                        .name("dog2")
                                                        .birthday(LocalDate.now())
                                                        .color(Color.BLUE).build(),
                                                     Dog.builder()
                                                        .id(3L)
                                                        .name("dog3")
                                                        .birthday(LocalDate.now())
                                                        .color(Color.YELLOW)
                                                        .build());
}
