package com.example.domain;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PetValidator {

    private final List<Pet> petList;

    public void validate(String petType) {
        petList.stream().filter(pet -> petType.equals(pet.type())).forEach(Pet::cry);
    }

    public void validateV2(String petType) {
        switch (petType) {
            case "CAT" -> new Cat().cry();
            case "DOG" -> new Dog().cry();
            default -> System.out.println("invalid");
        }
    }
}
