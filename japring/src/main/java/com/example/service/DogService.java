package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Dog;
import com.example.repository.DogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DogService {

    private final DogRepository dogRepository;

    public Dog getDog(int id) {
        return dogRepository.getDog(id);
    }

    public List<Dog> getDogList() {
        return dogRepository.getDogList();
    }
}
