package com.example.repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.domain.Cat;
import com.example.domain.Color;

@Repository
public class CatRepository {

    public List<Cat> getCatList() {
        return catList;
    }

    public Cat getCat(int id) {
        return catList.get(id);
    }

    private static List<Cat> catList = Arrays.asList(Cat.builder()
                                                        .id(1L)
                                                        .name("cat1")
                                                        .birthday(LocalDate.now())
                                                        .color(Color.RED).build(),
                                                     Cat.builder()
                                                        .id(2L)
                                                        .name("cat2")
                                                        .birthday(LocalDate.now())
                                                        .color(Color.BLUE).build(),
                                                     Cat.builder()
                                                        .id(3L)
                                                        .name("cat3")
                                                        .birthday(LocalDate.now())
                                                        .color(Color.YELLOW)
                                                        .build());
}
