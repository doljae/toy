package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Cat;
import com.example.repository.CatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatService {
    private final CatRepository catRepository;

    public Cat getCat(int id) {
        return catRepository.getCat(id);
    }

    public List<Cat> getCatList() {
        return catRepository.getCatList();
    }
}
