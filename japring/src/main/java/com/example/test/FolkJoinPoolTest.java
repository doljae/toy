package com.example.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FolkJoinPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        final List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            futures.add(executorService.submit(() -> {
                Thread.sleep(5000);
                return Thread.currentThread().getName() + ", index=" + index + ", ended at " + new Date();
            }));
        }

        for (Future<String> future : futures) {
            final String result = future.get();
            log.info("Thread result: " + result);
        }
        executorService.shutdown();
    }
}

