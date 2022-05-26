package com.example.repository;

import org.springframework.stereotype.Repository;

@Repository
public class CustomRepository {

    public void save(){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
